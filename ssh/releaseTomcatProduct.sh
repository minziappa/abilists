#!/bin/sh
#
# This is the shell script to release for something.
#
###############
# -eq -> ==
# -ne -> !=
# -gt -> >
# -lt -> <
# -ge -> >=
# -le -> <=
################

APP_NAME=tomcat
APP_PATH="/usr/local/tomcat/webapps/ROOT"

echo ${APP_PATH}

case "$1" in
download)

	status=`/usr/bin/wget --no-check-certificate https://jenkins:8081/view/abilists_pro/ws/build/libs/ROOT.war`
	echo "${status}"

;;
start)

	# Check the process count
	APP_CNT=`/bin/ps -ef | /bin/grep "tomcat" | /bin/grep -v "grep\|init.d" -c`
	if [ ${APP_CNT} -gt 0 ]; then
	        echo "Tomcat is already runing..."
	        exit 1
	fi

	if [ -d "${APP_PATH}" ]; then
		echo "If the path exists."
		/bin/rm -rf ${APP_PATH}
		/bin/rm /usr/share/tomcat7/webapps/geppo.war
	fi

	/bin/chmod 755 /home/cis_user/geppo.war 
	/bin/mv /home/cis_user/geppo.war /usr/share/tomcat/webapps/ROOT.war
	/bin/chown njoonk.njoonk /usr/share/tomcat/webapps/ROOT.war

	echo "the copy finished"

	printf "%-50s" "Starting $NAME..."
	/etc/init.d/${APP_NAME} start
	RETVAL=$?
	if [ $RETVAL -eq 0 ]; then
    	echo "successful start"
	else
    	echo "failure starting"
	exit 1
	fi

;;
stop)
    printf "%-50s" "Starting $NAME..."

    /etc/init.d/${APP_NAME} stop
    RETVAL=$?
    if [ $RETVAL -eq 0 ]; then
            echo "successful stop"
    else
            echo "failure stopping"
    fi
;;

*)
	echo "Usage: $0 {download|start|stop}"
	exit 1
esac