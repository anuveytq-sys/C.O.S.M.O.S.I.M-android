#!/usr/bin/env bash
# Run this inside the extracted repo folder. Requires git and a GitHub login (or token).
set -e
git init -b main
git add .
git commit -m "Initial commit: C.O.S.M.O.S.I.M Android (JARVIS HUD wrapper)"
git remote add origin https://github.com/anuveytq-sys/C.O.S.M.O.S.I.M-android.git
git push -u origin main
