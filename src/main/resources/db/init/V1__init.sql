CREATE TABLE "folders" (
   "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid())
);

CREATE TABLE "photos" (
  "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
  "description" text,
  "fk_folder" uuid
);

ALTER TABLE "photos" ADD FOREIGN KEY ("fk_folder") REFERENCES "folders" ("id");
