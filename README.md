# JavaBasic

Java を Visual Studio Code で学ぶための環境一式です。

## 対象環境

- Windows11
- Visual Studio Code (以下 VSCode)

## 構成

- Java 21
- devContainer

## 前提

- VSCode が導入されていること
- (Windows) WSL2 が導入されていること
- Docker 環境が導入されていること
  - Docker Desktop または Rancher Desktop

## 拡張機能

- [RedHat Java 支援パック redhat.java](https://marketplace.visualstudio.com/items?itemName=redhat.java)
- [Java 拡張機能セット vscjava.vscode-java-pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [エディターの標準スタイルの指定 EditorConfig.EditorConfig](https://marketplace.visualstudio.com/items?itemName=EditorConfig.EditorConfig)
- [エラー展開表示 usernamehw.errorlens](https://marketplace.visualstudio.com/items?itemName=usernamehw.errorlens)
- [全角スペースの明示 mosapride.zenkaku](https://marketplace.visualstudio.com/items?itemName=mosapride.zenkaku)
- [タブの色付け表示 oderwat.indent-rainbow](https://marketplace.visualstudio.com/items?itemName=oderwat.indent-rainbow)

## 導入

1. 推奨拡張機能を導入します
1. `開発コンテナーで開く` を行います
1. しばらく待ち、ターミナルに`Java 環境が正常に導入されました` が表示されるのを確認します
1. 導入完了です

## 使い方

以下は一例となります。

1. workspace 配下でディレクトリを作成します
1. ディレクトリにソースコード `*.java` を 追加します
1. 内容を記述します
1. `F5` キーを入力し、デバッグ実行します

launch.json で `編集中のディレクトリで実行する` 設定しているので `F5 による起動` をお勧めします。ただし、`mainメソッドがないソースコード`は実行できません。

`F5` でデバッグ起動、`Shift + F5` でデバッグなし起動になります。

## FAQ

### MariaDB のを初期化したい

```shell
docker rm spring-iot-temperature-logger-db-1
```

```shell
docker volume rm spring-iot-temperature-logger_mysql-data
```
