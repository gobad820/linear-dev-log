#!/bin/bash
# 내 구현 vs _solution 비교

LAB_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SOL_DIR="$LAB_DIR/_solution"

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
CYAN='\033[0;36m'
BOLD='\033[1m'
NC='\033[0m'

if [ ! -d "$SOL_DIR" ]; then
    echo "_solution/ 디렉토리가 없습니다."; exit 1
fi

TARGET="${1:-}"

echo -e "${BOLD}📋 정답 비교 — 내 구현 vs _solution${NC}"
echo "================================================="

find "$LAB_DIR/src" \( -name "*.java" -o -name "*.jsp" \) | sort | while read -r lab_file; do
    rel="${lab_file#$LAB_DIR/}"
    sol_file="$SOL_DIR/$rel"

    [ -n "$TARGET" ] && [[ "$lab_file" != *"$TARGET"* ]] && continue
    [ ! -f "$sol_file" ] && continue

    if diff -q "$lab_file" "$sol_file" > /dev/null 2>&1; then
        echo -e "${GREEN}✓ $rel${NC}"
        continue
    fi

    echo ""
    echo -e "${YELLOW}${BOLD}📄 $rel${NC}"

    diff "$lab_file" "$sol_file" | while IFS= read -r line; do
        case "$line" in
            "< "*)  echo -e "${RED}  내코드 │${NC} ${line:2}" ;;
            "> "*)  echo -e "${GREEN}  정답   │${NC} ${line:2}" ;;
            "---")  echo -e "          ┄┄┄┄┄┄┄┄┄┄" ;;
            [0-9]*) echo -e "${CYAN}  L$line${NC}" ;;
        esac
    done
    echo ""
done

echo "================================================="
echo -e "${CYAN}사용법: ./check_answer.sh [파일명]  예) ./check_answer.sh BookDaoImpl${NC}"
