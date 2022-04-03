### Jwt

- Generazione le chiavi (pubblica e privata) di cifratura

  ```shell
    # Generazione delle chiavi (pubblica e privata)
    openssl req -nodes -x509 -newkey rsa:4096 -keyout private_key_jwt.pem -out public_key_jwt.pem -days 7300 -subj '/CN=localhost'
  ```