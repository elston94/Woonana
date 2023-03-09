<template>
  <div>
    <div class="single-post-area Jua">
      <blockquote>
        <p>현재 주변에서 모집 진행중인 모임을 확인할 수 있습니다.</p>
        <cite>마커를 클릭하면 해당 게시글로 이동됩니다.</cite>
      </blockquote>
    </div>
    <div id="map"></div>
  </div>
</template>

<script>
// import http from "@/util/index";
export default {
  data() {
    return {
      map: null,
      // markerPositions1: [
      //   {
      //     title: "카카오",
      //     latlng: new kakao.maps.LatLng(33.450705, 126.570677),
      //   },
      //   {
      //     title: "생태연못",
      //     latlng: new kakao.maps.LatLng(33.450936, 126.569477),
      //   },
      //   {
      //     title: "텃밭",
      //     latlng: new kakao.maps.LatLng(33.450879, 126.56994),
      //   },
      //   {
      //     title: "근린공원",
      //     latlng: new kakao.maps.LatLng(33.451393, 126.570738),
      //   },
      // ],
      markers: [],
      places: [],
      myposition: [],
      curlat: null,
      curlong: null,
      index: "",
    };
  },
  computed: {
    myplace: function () {
      return this.$store.getters.get_place;
    },
    myposts: function () {
      //그 게시글 아이디들
      return this.$store.getters.get_postids;
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.2429362, 131.8624647, 16),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);

      // this.displayMarkers();
      this.getUserLocation();
    },
    displayMarkers(pos) {
      //현제 표시되어있는 마커가 있으면 marker에 등록된 map을 없애준다.
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }
      //2. 마커 이미지 커스터마이징 하기
      //javascript 영역에서 assets의 정보 가져오기
      const imgSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      const imgSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      //3. 마커 표시하기
      // console.log("여기 들어오나");
      // console.log(this.myplace);
      let self = this;
      for (var i = 0; i < this.myplace.length; i++) {
        var address = this.myplace[i];
        const postid = this.myposts[i];
        var router = this.$router;
        var geocoder = new kakao.maps.services.Geocoder();
        console.log("여긴 주소", address);
        geocoder.addressSearch(address, function (result, status) {
          if (status == kakao.maps.services.Status.OK) {
            //현재 위치에서 맨하탄 거리 차이 계산해서 넣을지 안넣을지 판단.
            console.log(pos.coords.latitude);
            console.log(pos.coords.longitude);
            //polyline을 이용해 두 좌표사이 거리 구한다.
            var polyline = new window.kakao.maps.Polyline();
            var newpath = [
              new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
              new kakao.maps.LatLng(result[0].y, result[0].x),
            ];
            //폴리라인 설정
            polyline.setPath(newpath);
            //두 점사이 거리!!!
            console.log(polyline.getLength());
            if (polyline.getLength() >= 2500) {
              //나중에 2KM으로 수정할것!!! 2000
              // 일정거리 이상이면함수종료
              console.log("종료");
              return;
            }
            if (!navigator.geolocation) {
              throw "위치 정보가 지원되지 않습니다.";
            }
            // console.log(this.mypositions.x, this.mypositions.y);
            console.log(result[0].y, result[0].x);
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            console.log(result);

            const marker = new kakao.maps.Marker({
              map: self.map,
              position: coords,
              title: postid,
              image: markerImage,
            });
            //마커 클릭이벤트 발생하게 해준다.

            window.kakao.maps.event.addListener(marker, "click", function () {
              // alert(postid);
              router.push({
                name: "ShowBlog",
                query: { data: postid },
              });
            });

            self.markers.push(marker);

            // console.log(marker);
            // var infowindow = new kakao.maps.InfoWindow({});
            // infowindow.open(this.map, marker);

            // // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            self.map.setCenter(
              new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude)
            );
          }
        });
      } //end for
      // console.log(self.markers);
      // const marker = new kakao.maps.Marker({
      //   map: this.map,
      //   position: position.latlng,
      //   title: position.title,
      //   image: markerImage,
      // });
      // this.markers.push(marker);
      // });

      //4. 지도를 이동시켜주기
      // const bounds = positions.reduce(
      //   (bounds, position) => bounds.extend(position.latlng),
      //   new kakao.maps.LatLngBounds()
      // );
      // this.map.setBounds(bounds);
    },
    addTolat() {
      this.markerPositions1 = [];
      //주소리스트를 좌표로 반환해주는 함수!!!!
      var geocoder = new window.kakao.maps.services.Geocoder();
      var callback = function (result, status) {
        //여기서 우리 뿌릴 거기에다가 데이터 교체하자!!!!
        if (status === window.kakao.maps.services.Status.OK) {
          // console.log(result);
          // console.log(result[0].address.x);

          // 변환된 좌표 넣기
          let tmp = {
            title: "test",
            latlng: new window.kakao.maps.LatLng(
              result[0].address.y,
              result[0].address.x
            ),
          };
          console.log(tmp);
          this.markerPositions1.push(tmp);
        }
      };
      for (var i = 0; i < this.testGetters.length; i++) {
        geocoder.addressSearch(this.testGetters[i], callback);
      }
      console.log("좌표변환완료!!!", this.markerPositions1);
    },

    success({ coords }) {
      //현재위치 콜백함수
      const latitude = coords.latitude; // 위도
      const longitude = coords.longitude; // 경도
      this.myposition = { x: latitude, y: longitude };
    },
    getUserLocation() {
      //현재위치 반환해주는 함수!!!!
      if (!navigator.geolocation) {
        throw "위치 정보가 지원되지 않습니다.";
      }
      navigator.geolocation.watchPosition(this.displayMarkers);
    },
    savecurposition(x, y) {
      this.curlat = x;
      this.curlong = y;
      console.log(this.curlat, this.curlong);
    },
  },
  beforeMount() {},
  mounted() {
    this.getUserLocation();
    this.$store.dispatch("getPlace");
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      // const APIKEY = "da329a02ea7df9bb610bd1a098b81c10";

      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=3e10ef66a0622fef2601f14a6c0ffb9e&libraries=services`;
      /*global kakao */
      script.addEventListener("load", () => {
        window.kakao.maps.load(this.initMap);
        this.displayMarkers(this.markerPositions1);
      });
      document.head.appendChild(script);
    } else {
      console.log("이미 로딩됨", window.kakao);
      this.initMap();
    }
  },
};
</script>

<style>
#map {
  width: 375px;
  height: 300px;
}
</style>
