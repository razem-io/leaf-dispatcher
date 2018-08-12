FROM hseeberger/scala-sbt:8u171_2.12.6_1.2.1 as build
RUN mkdir /build
WORKDIR /build
COPY . .
RUN sbt universal:packageZipTarball


FROM java:8-jre-alpine as publish
COPY --from=build /build/target/universal /leaf-dispatcher
WORKDIR /leaf-dispatcher
RUN apk add --no-cache tar bash && tar -xvzf *.tgz -C . --strip-components=1 && rm *.tgz

ENTRYPOINT ["bin/leaf-dispatcher"]