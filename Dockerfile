FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu AS builder

WORKDIR /workspace

# 必要に応じて追加パッケージをインストール
# RUN apt-get update && apt-get install -y ...
# 開発ツール追加
# グラフ生成のため、フォント関連を追加
RUN apt-get update && apt-get install -y \
    git \
    curl \
    fontconfig \
    fonts-noto-cjk \
    && rm -rf /var/lib/apt/lists/*

# 非rootユーザー作成
RUN useradd -m -s /bin/bash vscode
USER vscode

WORKDIR /workspace

CMD ["sleep", "infinity"]
