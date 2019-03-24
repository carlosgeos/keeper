(ns keeper.core
  (:gen-class
   :methods [^:static [handler [Object] Object]])
  (:require [cheshire.core :as che]
            [environ.core :refer [env]]
            [ring.util.codec :refer [form-decode]]
            [sendgrid.core :as sg]))

;;; Because this is on AWS Lambda + API Gateway, there is no need for
;;; any HTTP servers or routing middleware. Just fetch from DB and
;;; return JSON

;;; AWS Lambda proxy response format
;;; {
;;;    "isBase64Encoded": true/false,
;;;    "statusCode": 200,
;;;    "headers": {},
;;;    "body": "The body" ;; <-- stringified body !
;;; }

(def lambda_default
  {"isBase64Encoded" false
   "headers" {}
   "statusCode" 200})

(defn -handler
  "API Gateway transforms the original request quite a bit. It
  streamlines everything into a POST request

  req comes in as java.util.LinkedHashMap.

  API Gateway sends the POST contents inside the `body` key of req,
  and the SMS content is found inside (www-form-urlencoded) => the
  `Body` key"
  [req]

  (if-let [body (get (into {} req) "body")]
    (sg/send-email {:api-token (str "Bearer " (env :sendgrid-api-key))
                    :from "keeper@sendgrid.com"
                    :to (env :destination)
                    :subject "Keeper note"
                    :message (get (form-decode body) "Body")}))

  (merge lambda_default {"body" (che/generate-string req)}))
