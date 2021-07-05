# shadow-electron-starter
ClojureScript + Shadow-cljs + Electron + Reagent

## How to Run
```
yarn global add electron-prebuilt
yarn global add shadow-cljs
yarn

yarn run dev
--> new teerminal ->
yarn electron .
```

## Release
```
npm run build
electron-packager . HelloWorld --platform=darwin --arch=x64 --version=1.4.13
```
