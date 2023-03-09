<template>
  <ICountUp
    :delay="delay"
    :endVal="endVal"
    :options="options"
    @ready="onReady"
  />
</template>

<script>
import ICountUp from "vue-countup-v2";
export default {
  props: ["endVal"],
  components: { ICountUp },
  data() {
    return {
      delay: 1000,
      options: {
        useEasing: true,
        useGrouping: true,
        separator: ",",
        decimal: ".",
        prefix: "",
        suffix: "",
      },
    };
  },
  methods: {
    onReady(instance) {
      const that = this;
      instance.update(that.endVal);
    },
  },
};
</script>

<style>
</style>