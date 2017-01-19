#!/bin/sh

# This is a release file for the product.

NOW=$(date +"%m-%d-%Y")

cd ~

if [ -d "~/ROOT" ]; then
    echo "There is a folder"
else
    /bin/mkdir ./ROOT
    echo "There is no"
fi

cp ~/ROOT.war ~/ROOT/
cd ~/ROOT/
/usr/bin/jar xvf ./ROOT.war
/bin/rm ./ROOT.war

cp ~/product/application.properties ~/ROOT/WEB-INF/classes/
cp ~/product/resources/config.xml ~/ROOT/WEB-INF/classes/resources/

mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/temp/ROOT_${NOW}
cp -r ~/ROOT /usr/local/tomcat/webapps/

exit 0