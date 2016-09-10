# ARTIK IDE DEVELOPMENT

![Contribute](http://beta.codenvy.com/factory/resources/codenvy-contribute.svg)](https://beta.codenvy.com/dashboard/#/load-factory?id=95txzq1w5v5hy9or)

[![Eclipse License](http://img.shields.io/badge/license-Eclipse-brightgreen.svg)](https://github.com/codenvy/che/blob/master/LICENSE)

The repo is to provide helpful software/scripts for the artik board. In general these tools will be to enhance ARTIK-IDE.

To use build the dockerfile.

```shell
cd /projects/artik-tools/
mvn clean install
#run from host or workspace with docker support.
docker build -t jdrummond/artik-tools .
docker run jdrummond/artik-tools
```
