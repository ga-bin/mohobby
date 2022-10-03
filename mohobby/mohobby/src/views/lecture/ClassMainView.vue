<template>
  <div>
    <ClassSidebar></ClassSidebar>
    <button @click="clearSession">clearSession</button><br/>
    <div>
      <h3>사용자 인증</h3>
      <span>code: {{ auth.code }}</span><br/>
      <span>scope: {{ auth.scope }}</span><br/>
      <span>client_info: {{ auth.client_info }}</span><br/>
      <span>state: {{ auth.state }}</span><br/><br/>
      <form method="get" action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" target="_blank">
        <input type="hidden" name="response_type" value="code"/>
        <input type="hidden" name="client_id" value="09076cd0-82f3-4c5b-a982-862c4c655cca"/>
        <input type="hidden" name="redirect_uri" value="http://localhost:8088"/>
        <input type="hidden" name="scope" value="login inquiry transfer"/>
        <input type="hidden" name="state" value="b80BLsfigm9OokPTjy03elbJqRHOfGSY"/>
        <input type="hidden" name="auth_type" value="0"/>
        <input type="submit" value="requestAuth"/>
      </form>
      <button @click="saveAuth">saveAuth</button>
      </div>
  </div>
</template>
<script>
import ClassSidebar from "../../components/lecture/ClassSidebar.vue";
export default {
  components: { ClassSidebar },
  data() {
    return {
      sampleData: "",
      auth: {
        code: '',
        scope: '',
        client_info: '',
        state: ''
      }
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    clearSession () {
      sessionStorage.auth = ''
      sessionStorage.token = ''
      this.auth = {
        code: '',
        scope: '',
        client_info: '',
        state: ''
      }
      this.token = {
        access_token: '',
        expires_in: '',
        refresh_token: '',
        scope: '',
        token_type: '',
        user_seq_no: ''
      }
    },
    saveAuth () {
      sessionStorage.auth = JSON.stringify({
        code: this.$route.query.code,
        scope: this.$route.query.scope,
        client_info: this.$route.query.client_info,
        state: this.$route.query.state
      })
      this.auth = JSON.parse(sessionStorage.auth)
    }
  },
};
</script>

<style></style>
