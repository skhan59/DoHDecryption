#/bin/bash

#Make sure to start wireshark first and record traffic
while read line; do
	echo $line
	firefox --new-tab $line &
	sleep 30
	killall firefox-esr
	sleep 10
	rm -Rf /home/saad/.mozilla/firefox/v7k0ucrc.default-release/sessionstore-backups
done < sites.txt
