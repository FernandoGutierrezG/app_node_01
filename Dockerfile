FROM node:latest

WORKDIR /app

COPY package.json yarn.lock ./

RUN yarn install --production

COPY build/ ./

CMD ["node", "index.js"]

