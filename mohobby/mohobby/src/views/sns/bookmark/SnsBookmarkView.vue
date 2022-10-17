<template>
  <Collection />
</template>
<script>
    import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
    import Collection from "@/components/sns/Bookmark/Collection.vue";
    export default {
      name: "BookMarkMain",
      components: { SnsSidebar, Collection },
      data() {
        return {
          collectons:[
            {colName:"컬렉션1"},
            {colInfo:"his is a short description"},
            {colImg:"https://images.unsplash.com/photo-1433360405326-e50f909805b3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max&s=359e8e12304ffa04a38627a157fc3362"},
          ],
        }
      },
      setup() {
        
      },
      created() {
    
      },
      mounted() {
    
      },
      unmounted() {
    
      },
      methods: {
    
      },
    };
</script>
  
<style scoped>
  @import url(https://fonts.googleapis.com/css?family=Raleway);

  *, *:before, *:after{

    margin: 0 auto;
    width: 80%;
    padding: 0;
    -webkit-box-sizing: border-box;
    -moz-box-sizing:border-box;
    box-sizing: border-box;
  }

  body{
    background: #f9f9f9;
    font-size: 16px;
    font-family: 'Raleway', sans-serif;
  }

  .main-title{
    color: #2d2d2d;
    text-align: center;
    text-transform: capitalize;
    padding: 0.7em 0;
  }

  .container{
    padding: 1em 0;
    float: left;
    width: 50%;
  }
  @media screen and (max-width: 640px){
    .container{
      display: block;
      width: 100%;
    }
  }

  @media screen and (min-width: 900px){
    .container{
      width: 33.33333%;
    }
  }

  .container .title{
    color: #1a1a1a;
    text-align: center;
    margin-bottom: 10px;
  }

  .content {
    position: relative;
    width: 90%;
    max-width: 400px;
    margin: auto;
    overflow: hidden;
  }

  .content .content-overlay {
    background: rgba(0,0,0,0.7);
    position: absolute;
    height: 99%;
    width: 100%;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    opacity: 0;
    -webkit-transition: all 0.4s ease-in-out 0s;
    -moz-transition: all 0.4s ease-in-out 0s;
    transition: all 0.4s ease-in-out 0s;
  }

  .content:hover .content-overlay{
    opacity: 1;
  }

  .content-image{
    width: 100%;
  }

  .content-details {
    position: absolute;
    text-align: center;
    padding-left: 1em;
    padding-right: 1em;
    width: 100%;
    top: 50%;
    left: 50%;
    opacity: 0;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    -webkit-transition: all 0.3s ease-in-out 0s;
    -moz-transition: all 0.3s ease-in-out 0s;
    transition: all 0.3s ease-in-out 0s;
  }

  .content:hover .content-details{
    top: 50%;
    left: 50%;
    opacity: 1;
  }

  .content-details h3{
    color: #fff;
    font-weight: 500;
    letter-spacing: 0.15em;
    margin-bottom: 0.5em;
    text-transform: uppercase;
  }

  .content-details p{
    color: #fff;
    font-size: 0.8em;
  }

  .fadeIn-bottom{
    top: 80%;
  }

  .fadeIn-top{
    top: 20%;
  }

  .fadeIn-left{
    left: 20%;
  }

  .fadeIn-right{
    left: 80%;
  }  
</style>
  