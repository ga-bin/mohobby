<template>
  <div>
    <SnsSidebar />
    <Detail />
  </div>
</template>
<script>
  import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
  import Detail from "@/components/sns/Bookmark/Detail.vue";
  export default {
    name: "BookmarkDetail",
    components: { SnsSidebar, Detail },
  }
</script>