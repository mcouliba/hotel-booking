#!/bin/bash

oc delete pods -ldeploymentconfig=customer-service -n hotel-booking-customer &
oc delete pods -ldeploymentconfig=inventory-service -n hotel-booking-inventory &
oc delete pods -ldeploymentconfig=reservation-service -n hotel-booking-reservation &
oc delete pods -ldeploymentconfig=hotel-booking-web-app -n hotel-booking-web-app &
#oc delete pods -ldeploymentconfig=virtual-layer -n hotel-booking-virtual-layer &
