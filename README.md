# Cross-Cloud Cost Analytics Engine

## Quick start

```bash
# Generate 300 k sample rows for AWS
./gradlew :generator:run --args="-p aws -n 300000 -o aws_cur.csv"
# Same inside Docker
docker run --rm -v $PWD:/data cloudintel/generator:0.1 \
           -p gcp -n 300000 -o /data/gcp_billing.csv

Commit:

```bash
git add README.md
git commit -m "docs: add quick-start commands for generator"
git push
