# Keeper (SMS -> email)

Flow:

SMS to Twilio -> Webhook -> API Gateway -> Lambda -> Sendgrid SMTP -> Deliver Email

## Usage

```
$ lein uberjar
```
