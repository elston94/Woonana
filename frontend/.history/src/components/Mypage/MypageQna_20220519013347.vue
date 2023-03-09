<template>
  <div>
    <VueFaqAccordion :items="myItems" />
  </div>
</template>

<script>
import VueFaqAccordion from "vue-faq-accordion";

export default {
  components: {
    VueFaqAccordion,
  },
  data() {
    return {
      myItems: [
        {
          title: "Q. 프로필을 변경하고싶어요",
          value: "A. 카카오 프로필을 가져와서 불가능합니다.",
          category: "Q&A",
        },
        {
          title: "Q. 커뮤니티 가이드라인이 궁금해요",
          value: "A. 과도한 욕설과 도배는 정지사유가 됩니다.",
          category: "Q&A",
        },
        {
          title: "Q. 매너점수 평가기준이 궁금해요",
          value: "A. 모든 사용자들이 준 별점의 평균입니다.",
          category: "Q&A",
        },
      ],
    };
  },
};
</script>

<style>
.faq {
  margin: 7%;
}
</style>
