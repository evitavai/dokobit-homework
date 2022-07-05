## Getting started

- Run `docker compose up -d` to launch service with the database (PostgreSQL) instance locally.

## 1.**If we add multiple archiving methods (7z, for example)**

We can add an additional param, like "archivingMethod" to the **createZip** method and then use method overload
depending on the set archivingMethod.

## 2. **Face a significant increase in request count**

.

## **3. Allow 1GB max file size**

Streams are currently used for zipping logic so increase in the file size should not make a huge impact. Would just need
to change _spring.servlet.multipart.max-file-size_ property.
