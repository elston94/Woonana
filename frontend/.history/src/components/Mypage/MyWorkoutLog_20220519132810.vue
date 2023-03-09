<template>
  <div id="myworkoutlog-container">
    <Pie
      :chart-options="chartOptions"
      :chart-data="mychartData"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    />
    <!--뷰차트 화면보이게 해주는거-->
    <table id="customers">
      <tr>
        <th>총 운동 횟수 :</th>
        <td>
          <b-img
            rounded="circle"
            src="https://static.thenounproject.com/png/118627-200.png"
            alt="Image 3"
            fluid
            height="75"
            width="75"
          ></b-img>
          {{ mymonthlog.excAll }}
        </td>
      </tr>
      <tr>
        <th>이번달 운동횟수 :</th>
        <td>
          <b-img
            rounded="circle"
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT38P7qYHj1016Ca6O06FwKHontRCtgHsAhIg&usqp=CAU"
            alt="Image 3"
            fluid
            height="75"
            width="75"
          ></b-img
          >{{ mymonthlog.excMonthCnt }}
        </td>
      </tr>
      <tr>
        <th>이번달 탁구 :</th>
        <td>
          <b-img
            rounded="circle"
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcunkaZrK-ae4UsHmK4QaCNfZ1Up3To2sOsQ&usqp=CAU"
            alt="Image 3"
            fluid
            height="75"
            width="75"
          ></b-img>
          {{ mymonthlog.monthLogs.length >= 1 ? mymonthlog.monthLogs[0].exerciseCount : 0 }}
        </td>
      </tr>
      <tr>
        <th>이번달 배드민턴 :</th>
        <td>
          <b-img
            rounded="circle"
            src="https://previews.123rf.com/images/redlinevector/redlinevector1703/redlinevector170311550/74527882-%EB%B0%B0%EB%93%9C%EB%AF%BC%ED%84%B4-%EC%95%84%EC%9D%B4%EC%BD%98.jpg"
            alt="Image 3"
            fluid
            height="75"
            width="75"
          ></b-img
          >{{ mymonthlog.monthLogs.length >= 3 ? mymonthlog.monthLogs[2].exerciseCount : 0 }}
        </td>
      </tr>
      <tr>
        <th>이번달 캐치볼 :</th>
        <td>
          <b-img
            rounded="circle"
            src="https://static.thenounproject.com/png/1188406-200.png"
            alt="Image 3"
            fluid
            height="75"
            width="75"
          ></b-img
          >{{ mymonthlog.monthLogs.length >= 2 ? mymonthlog.monthLogs[1].exerciseCount : 0 }}
        </td>
      </tr>
      <tr>
        <th>이번달 산책 :</th>
        <td>
          <b-img
            rounded="circle"
            src="https://cdn-icons-png.flaticon.com/512/10/10869.png"
            alt="Image 3"
            fluid
            height="75"
            width="75"
          ></b-img
          >{{ mymonthlog.monthLogs.length >= 4 ? mymonthlog.monthLogs[4].exerciseCount : 0 }}
        </td>
      </tr>
    </table>
    <vuejs-heatmap :entries="this.myheatmapData" :color-range="this.colorRangeData"></vuejs-heatmap>
    <br />
  </div>
</template>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<script>
import { Pie } from "vue-chartjs/legacy";
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale } from "chart.js";
ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale);
export default {
  name: "PieChart",
  components: {
    Pie,
  },
  props: {
    chartId: {
      type: String,
      default: "pie-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      //넣을 차트데이터 예시,,, 서버에서 가져온 데이터로 대체한다.
      chartData: {
        labels: ["VueJs", "EmberJs", "ReactJs", "AngularJs"],
        datasets: [
          {
            backgroundColor: ["#41B883", "#E46651", "#00D8FF", "#DD1B16"],
            data: [4, 2, 8, 1],
          },
        ],
      },
      // chartData: {
      //   labels: [],
      //   datasets: [
      //     {
      //       backgroundColor: [],
      //       data: [],
      //     },
      //   ],
      // },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
      },
      exerciseData: [],
      heatmapData: [],
      colorRangeData: ["#c9ecec", "#09b3af"],
    };
  },
  methods: {
    //ChartData를 가져온 값으로 재설정한다.
    setExercisedata: function () {
      this.chartData.datasets[0].data = [];
      this.chartData.labels = [];
      for (let i = 0; i < this.myinfomation.length; i++) {
        // console.log("formoon work");
        this.chartData.datasets[0].data.push(this.myinfomation[i].exerciseCount);
        this.chartData.labels.push(this.myinfomation[i].exerciseName);
      } // end for
      //최대 4개의 색상중 들어간 갯수만 남기고 지우기
      this.chartData.datasets[0].backgroundColor.splice(
        this.myinfomation.length + 1,
        /*this.chartData.datasets.backgroundColor.length*/ 4 - this.myinfomation.length
      );
      // console.log(this.chartData.datasets[0].data);
      // console.log(this.chartData.labels);
    },
    setWorkoutData: function () {
      // 한달 운동기록 데이터 수정
      this.workoutlogs[0].count = mymonthlog.excAll;
      this.workoutlogs[1].count = mymonthlog.excMonthCnt;
      for (let i = 0; i < mymonthlog.monthLogs.length; i++) {
        let index = mymonthlog.monthLogs[i].exerciseId + 1;
        this.workoutlogs[index].count = mymonthlog.monthLogs[i].exerciseCount;
        console.log(this.workoutlogs[index]);
      }
    },
    getHeatmapData: async function () {
      http.get("/api/accounts/myexercise/likes/");
    },
  },
  async mounted() {
    //유저 아이디 호출
    let tmp = await this.$store.dispatch("getUserId");
    console.log(tmp);

    await this.$store.dispatch("getUserInfo");
    await this.$store.dispatch("getAllExerciseInfo", tmp.userId); // userid에 해당하는 모든 운동로그 가져옴
    await this.$store.dispatch("getMonthExerciseInfo", tmp.userId); // userid에
    await this.$store.dispatch("getHeatmapInfo", tmp.userId); // userid에 해당하는 운동기록 가져옴

    console.log(this.myinfomation);
    console.log(this.mymonthlog);
    console.log(this.myheatmapData);
    this.setExercisedata();
    this.setWorkoutData(); //한달 운동기록을 서버에 얻어온 값으로 대체
    console.log(this.myheatmapData);
  },
  created() {
    console.log(this.myheatmapData);
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.get_all_exercise;
    },
    mymonthlog: function () {
      return this.$store.getters.get_month_Exercise;
    },
    mychartData: function () {
      return this.chartData;
    },
    myheatmapData: function () {
      let result = this.$store.getters.GET_HEATMAP_INFO;
      console.log(result);
      for (let i = 0; i < result.length; i++) {
        Object.defineProperty(
          result[i],
          "counting",
          Object.getOwnPropertyDescriptor(result[i], "exerciseId")
        );
        Object.defineProperty(
          result[i],
          "created_at",
          Object.getOwnPropertyDescriptor(result[i], "endDate")
        );

        delete result[i].exerciseId;
        delete result[i].endDate;
        console.log(result);
      }
      return result;
    },
  },
};
</script>

<style>
#myworkoutlog-container {
  width: 300px;
}
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 70%;
}

#customers td,
#customers th {
  border: 1px solid #ddd;
  padding: 2%;
}
#customers td:first-child,
#customers th:first-child {
  border-radius: 10px 0 0 10px;
}

#customers td:last-child,
#customers th:last-child {
  border-radius: 0 10px 10px 0;
}
#customers tr:nth-child(even) {
  background-color: #f2f2f2;
}

#customers tr:hover {
  background-color: #ddd;
}

#customers th {
  text-align: center;
  background-color: #0dcaf0;
  color: white;
  width: 55%;
}
#customers td {
  text-align: center;
  width: 60%;
}
</style>
