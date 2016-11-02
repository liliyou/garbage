/**
 *
 * MarkerInfo api
 *
 */
function generalPoint() {


    $.getJSON("garbgeCar.json", function(json) {

        var pointArray = json.result.results;

        for (var i = 0; i < pointArray.length; i++) {
            // for (var i = 0; i < 10; i++) {
            var beach = pointArray[i];

            // var contentString = document.createElement('div');
            var cln = detailView.cloneNode(true);


            $(cln).find('#CarTime').html("" + beach["CarTime"] + "");
            $(cln).find('#Region').html("" + beach["﻿Region"] + "");
            $(cln).find('#Li').html("" + beach["Li"] + "");
            $(cln).find('#Address').html("" + beach["Address"] + "");

            var marker = new google.maps.Marker({
                position: {
                    lat: Number(beach.Lat),
                    lng: Number(beach.Lng)
                },
                icon: './car.png'
            });

            marker.info = cln;
            var currentInfoWin = null;
            marker.addListener('click', function() {
                // this.infowindow.open(map, this);
                if (currentInfoWin !== null) {
                    currentInfoWin.close(map, this);
                }

                currentInfoWin = new google.maps.InfoWindow({
                    content: this.info
                });
                // console.log(" content ", this.info);
                currentInfoWin.open(map, this);
            });

            markerArray.push(marker);
            // marker.setMap(map);
        }
        var markerCluster = new MarkerClusterer(map, markerArray, { imagePath: 'images/m' });
    });


}

function drop() {
    for (var i = 0; i < markerArray.length; i++) {
        setTimeout(function() {
            addMarkerMethod();
        }, i * 200);
    }
}
