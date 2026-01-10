FROM node:20-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build -- --configuration production



FROM nginx:alpine

COPY nginx.conf.template /etc/nginx/conf.d/default.conf.template
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html/

EXPOSE 80

CMD /bin/sh -c "envsubst '\$GATEWAY_HOST \$GATEWAY_PORT' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"
