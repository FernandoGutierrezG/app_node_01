FROM node:16.14.2

WORKDIR /app

COPY /app .
#COPY package.json yarn.lock ./

RUN yarn install --production


CMD ["node", "index.js"]

