### Production build ###
# Base image as 'build' var
from node:8 as build
# Conventional working directory
run mkdir /usr/src/app
# Using that directory
workdir /usr/src/app
# Leverage Docker's cache layer by
# exposing all our Node.js binaries
env PATH /usr/src/app/node_modules/.bin:$PATH
# Copy package.json
copy package.json /usr/src/app/package.json
run npm install 

copy . /usr/src/app
cmd ["npm", "start"]

### Production environment ###
# from nginx:1.12-alpine
# Copy nginx conf
# run rm /etc/nginx/conf.d/default.conf
# copy nginx.conf /etc/nginx/conf.d
# Copy production build to server folder
# copy --from=build /usr/src/app/build /usr/share/nginx/html
# expose 80
# cmd ["nginx", "-g", "daemon off;"]