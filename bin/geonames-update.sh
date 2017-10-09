#!/usr/bin/env bash -x

# Update prod and test data from;
#  - http://download.geonames.org/export/dump/

SCRIPT_PATH="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SCRIPT_TMP_PATH="$SCRIPT_PATH/tmp"
PROJECT_PATH="$SCRIPT_PATH/.."
RESOURCES_PATH="$PROJECT_PATH/src/main/resources/geonames"
RESOURCES_TEST_PATH="$PROJECT_PATH/src/test/resources/geonames"

mkdir -p "$SCRIPT_TMP_PATH"
mkdir -p "$RESOURCES_PATH"
mkdir -p "$RESOURCES_TEST_PATH"

wget http://download.geonames.org/export/dump/countryInfo.txt -O "$SCRIPT_TMP_PATH/countryInfo.txt"
wget http://download.geonames.org/export/dump/cities1000.zip  -O "$SCRIPT_TMP_PATH/cities1000.zip"

unzip -o -d "$SCRIPT_TMP_PATH" "$SCRIPT_TMP_PATH/cities1000.zip"

cat "$SCRIPT_TMP_PATH/countryInfo.txt"             > "$RESOURCES_PATH/countryInfo.txt"
cat "$SCRIPT_TMP_PATH/cities1000.txt"              > "$RESOURCES_PATH/cities1000.txt"
cat "$SCRIPT_TMP_PATH/cities1000.txt" | grep "\tPL\t"  > "$RESOURCES_TEST_PATH/pl-cities1000.txt"

ls -al "$RESOURCES_PATH"
ls -al "$RESOURCES_TEST_PATH"
