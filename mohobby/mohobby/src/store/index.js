import Vue from "vue";
import Vuex from "vuex";

import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
    plugins : [createPersistedState()],
    modules : {
        
    },
    state : {
        isLogin : false,
        id : "",
        user:null,
    },
    getter : {
        isLogin(state) {
            return state.isLogin;
        }, getId(state) {
            return state.id;
        },
    },
    mutations : {
        setIsLoginTrue(state) {
            state.isLogin = true;
        },
        setIsLoginFalse(state) {
            state.isLogin = false;
        },
        sedId(state, memberId) {
            state.id = memberId;
        },
        setUserData(state, userData) {
            state.user = userData;
        },
        logout(state){
            state.id='';
        }
    },
    actions : { getUserData(context) {
        axios.get("url").then((resp) => {
            context.commit("setUserData", resp.data);
        })
    }},
})