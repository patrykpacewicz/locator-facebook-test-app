locator-facebook-test-app
=========================

Simple application to play with facebook graph api

### How to run

```bash
# Create configuration with facebook key

cat <<EOF > application-prod.yaml
locator:
  facebook:
    appId: "facebook-app-id"
    appSecret: "facebook-app-secret"
EOF

# Run application
SPRING_PROFILES_ACTIVE="prod" ./gradlew bootRun
```

Open [http://localhost:8080/documentation][app] in your favorite web browser

[app]: http://localhost:8080/documentation
