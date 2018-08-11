FROM hseeberger/scala-sbt:8u171_2.12.6_1.2.1 as build
RUN \
  apt-get update && \
  apt-get install git && \
  rm -rf /var/lib/apt/lists/*
RUN mkdir /build
WORKDIR /build
RUN \
  git clone https://github.com/razem-io/leaf-spy-dispatcher.git && \
  cd leaf-spy-dispatcher && \
  sbt universal:packageZipTarball


FROM java:8-jre-alpine as publish
COPY --from=build /build/leaf-spy-dispatcher/target/universal /leaf-spy-dispatcher
WORKDIR /leaf-spy-dispatcher
RUN apk add --no-cache tar bash && tar -xvzf *.tgz -C . --strip-components=1 && rm *.tgz

ENTRYPOINT ["bin/leaf-spy-dispatcher"]