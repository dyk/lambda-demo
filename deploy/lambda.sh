aws lambda create-function \
  --region us-west-2 \
  --function-name ProcessKinesisRecords  \
  --zip-file fileb://file-path/ProcessKinesisRecords.zip \
  --role execution-role-arn  \
  --handler handler \
  --runtime runtime-value \
  --profile adminuser
