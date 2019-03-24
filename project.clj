(defproject keeper "0.1.0-SNAPSHOT"
  :description "Turn an API Gateway POST request into an email"
  :url "https://github.com/carlosgeos/keeper"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.amazonaws/aws-lambda-java-core "1.2.0"]
                 [cheshire "5.8.1"]
                 [environ "1.1.0"]
                 [clj-sendgrid "0.1.2"]
                 [ring/ring-codec "1.1.1"]]
  :plugins [[lein-environ "1.1.0"]]
  :repl-options {:init-ns keeper.core}
  :aot :all
  :main keeper.core)
