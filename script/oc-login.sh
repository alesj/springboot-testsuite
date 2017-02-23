#!/usr/bin/env bash

#
# oc-login.sh -u admin -p admin -a https://localhost:8443 -n test
# oc-login.sh -t token -a https://localhost:8443 -n test
#

while getopts u:p:t:a:n: option
do
        case "${option}"
        in
                u) USER=${OPTARG};;
                p) PASSWORD=${OPTARG};;
                t) TOKEN=${OPTARG};;
                a) API=${OPTARG};;
                n) PROJECT=${OPTARG};;
        esac
done

if [ -z "$API" ] || [ -z "$PROJECT" ]; then
  echo "API & NAMESPACE parameters are mandatory. Example 'oc-login.sh -u admin -p admin -a https://localhost:8443 -n test'"
  exit
fi


echo "Log on to OpenShift Machine"
if [ "$TOKEN" != "" ]; then
   oc login $API --token=$TOKEN
else
   echo "oc login $API -u $USER -p $PASSWORD"
   oc login $API -u $USER -p $PASSWORD
fi

RESPONSE=$(oc project $PROJECT 2>&1)

if [[ $RESPONSE == *"error:"* ]]; then
    echo "Project $PROJECT does not exist. It will be created"
    oc new-project $PROJECT
else
    echo "Project $PROJECT already exists !"
fi