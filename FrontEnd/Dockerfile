FROM node:latest

# Establece una variable de entorno para el modo (development o production)
ENV NODE_ENV=development

RUN npm install -g @angular/cli@15.2.6
RUN mkdir /code
WORKDIR /code
COPY package.json /code/
ADD . /code/
RUN npm install
RUN npm update -D

CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200", "--configuration", "development"]

