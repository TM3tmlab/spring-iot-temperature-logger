# JavaBasic

Java を Visual Studio Code で学ぶための環境一式です。

## 構成

- Java 21
- devContainer

## 前提

- VSCode が導入されていること
- (Windows) WSL2 が導入されていること
- Docker 環境が導入されていること
  - Docker Desktop または Rancher Desktop

## 拡張機能

- RedHat Java支援パック redhat.java
- Java拡張機能セット vscjava.vscode-java-pack
- エディターの標準スタイルの指定 EditorConfig.EditorConfig
- 全角スペースの明示 mosapride.zenkaku

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
1. main メソッドがあれば その付近に `run | debug` の記述があるのでどちらかをクリックすると実行します
