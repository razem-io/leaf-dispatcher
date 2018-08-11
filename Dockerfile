FROM hseeberger/scala-sbt:8u171_2.12.6_1.2.1 as build
RUN mkdir /build
WORKDIR /build
RUN \
  apt-get update && \
  apt-get install git && \

COPY


FROM java:8-jre-alpine

ADD target/universal/ /leaftop

WORKDIR /leaftop

RUN apk add --no-cache tar bash && tar -xvzf *.tgz -C . --strip-components=1 && rm *.tgz

ENTRYPOINT ["bin/leaf-spy-dispatcher"]