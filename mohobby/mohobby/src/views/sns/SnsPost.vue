<template>
  <div class="card">
    <div :class="card-inner">
      <div class="card-text-wrap">
        <h2 class="bg-text">{{ card.cmtcnt }}</h2>
      </div>
      <div class="card_image_wrap">
        <img :src="card.src" class="image" />
      </div>
      <div class="card-detail">
        <h2>{{card.title}}</h2>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "snsPost",
  props : ["card"]
}
</script>

<style>
  .card {
    flex: 1 1 33.333%;
    width: 100%;
    padding: 25px;
  }
  .card-inner {
    position: relative;
    padding: 25px;
    box-shadow: 0px 0px 16px rgba(0,0,0,0.25);
  }
  .card-image-wrap .image {
    width: 100%;
  }
</style>
