# ARTIK IDE DEVELOPMENT

[![Contribute](http://beta.codenvy.com/factory/resources/codenvy-contribute.svg)](https://beta.codenvy.com/dashboard/#/load-factory?id=95txzq1w5v5hy9or)

[![Eclipse License](http://img.shields.io/badge/license-Eclipse-brightgreen.svg)](https://github.com/codenvy/che/blob/master/LICENSE)

The repo is to provide helpful software/scripts for the artik board. In general these tools will be to enhance ARTIK-IDE.

To use build the dockerfile.

```shell
##From Codenvy IDE
cd /projects/artik-tools/
mvn clean install
```

```shell
#From host or workspace CLI with docker support.
docker build -t jdrummond/artik-tools .
```

```shell
#Windows
for /f "skip=1 tokens=2 delims=: " %f in ('nslookup %COMPUTERNAME% ^| find /i "Address"') do set HOST_IP=%f
docker run -i jdrummond/artik-tools -t 5 -i %HOST_IP%
```

```shell
#Unix
#Change eth0 if using a different network device
export HOST_IP="$(ifconfig | grep -A 1 'eth0' | tail -1 | cut -d ':' -f 2 | cut -d ' ' -f 1)"
docker run -i jdrummond/artik-tools -t 5 -i $HOST_IP
```
