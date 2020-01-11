#!/bin/sh
curl --header "Content-Type: text/xml;charset=UTF-8" --header "SOAPAction:urn:PriceDetailsRequest" --data @req.xml http://localhost:8118/priceinfosoap/ws
