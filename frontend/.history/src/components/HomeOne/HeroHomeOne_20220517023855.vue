<template>
<!--====== APPIE HERO PART START ======-->
  <section class="appie-hero-area">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <div class="appie-hero-content">
                        <span>{{welcome_message}}</span>
                        <h1 class="appie-title">{{slogan}}</h1>
                        <!-- <p>{{description}}</p> -->
                        <ul>
                            <li><a href="/news">{{first_button_content}}</a></li>
                          
                            <!-- <li><a class="item-2" href="#"><i :class="second_button_icon"></i> {{second_button_content}}</a></li> -->
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="appie-hero-thumb">
                        <div class="thumb wow animated fadeInUp" data-wow-duration="2000ms" data-wow-delay="200ms">
                            <img src="@/assets/images/pre_designed_mainpage.png" alt="">
                        </div>
                        <!-- <div class="thumb-2 wow animated fadeInRight" data-wow-duration="2000ms" data-wow-delay="600ms">
                            <img src="@/assets/images/hero-thumb-2.png" alt="">
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
        <div class="hero-shape-1">
            <img src="@/assets/images/shape/shape-2.png" alt="">
        </div>
        <div class="hero-shape-2">
            <img src="@/assets/images/shape/shape-3.png" alt="">
        </div>
        <div class="hero-shape-3">
            <img src="@/assets/images/shape/shape-4.png" alt="">
        </div>
    </section>
    <!--====== APPIE HERO PART end ======-->
</template>

<script>
export default {
    props:['welcome_message','slogan','description','thumb_one','thumb_two','first_button_content','first_button_icon','second_button_content','second_button_icon']

}
</script>

<style>

</style>