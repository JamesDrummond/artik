# Copyright (c) 2012-2016 Codenvy, S.A., Red Hat, Inc
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#   James Drummond
#   Codenvy S.A
#
# To build it, run in the repository root:
#  `docker build -t codenvy/artiktools .`
#          

FROM alpine:3.4

ENV LANG=C.UTF-8 \
    JAVA_HOME=/usr/lib/jvm/default-jvm/jre \
    PATH=${PATH}:${JAVA_HOME}/bin \
    CHE_HOME=/home/user/che \
    DOCKER_VERSION=1.6.0 \
    DOCKER_BUCKET=get.docker.com

RUN echo "http://dl-4.alpinelinux.org/alpine/edge/community" >> /etc/apk/repositories && \
    apk upgrade --update && \
    apk add --update net-tools ca-certificates curl openssl openjdk8-jre sudo bash openssh sshpass && \
    curl -sSL "https://${DOCKER_BUCKET}/builds/Linux/x86_64/docker-${DOCKER_VERSION}" -o /usr/bin/docker && \
    chmod +x /usr/bin/docker && \
    addgroup -S -g 1000 user && \
    adduser -S user -h /home/user -s /bin/bash -G user -u 1000 -D && \
    addgroup -S docker -g 101 && \
    adduser user docker && \
    adduser user user && \
    adduser user users && \
    addgroup -g 50 -S docker4mac && \
    adduser user docker4mac && \
    echo "%root ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers && \
    rm -rf /tmp/* /var/cache/apk/*

EXPOSE 8000 8080

RUN mkdir /home/user/artik-tools
ADD target /home/user/artik-tools/
RUN mv /home/user/artik-tools/artik-ide-tools*.jar /home/user/artik-tools/artik-ide-tools.jar
ADD src /home/user/artik-tools/src
RUN chown -R user:user /home/user/

USER user

WORKDIR /home/user/artik-tools/

ENTRYPOINT ["java","-jar","artik-ide-tools.jar"]

