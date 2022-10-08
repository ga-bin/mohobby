<template>
    <div>
        <div>
            <h3>테스트베드 계좌 내역</h3>
           <table>
            <thead>
                <tr>
                    <th>은행</th>
                    <th>계좌번호</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>농협은행</td>
                    <td>3020000006953</td>
                </tr>
                <tr>
                    <td>농협은행</td>
                    <td>3020000006955</td>
                </tr>
                <tr>
                    <td>기업은행</td>
                    <td>1000001820003</td>
                </tr>
                <tr>
                    <td>KEB하나은행</td>
                    <td>1000001822081</td>
                </tr>
                <tr>
                    <td>산업은행</td>
                    <td>1000001814002</td>
                </tr>
                <tr>
                    <td>국민은행</td>
                    <td>1000001821004</td>
                </tr>
                <tr>
                    <td>우리은행</td>
                    <td>1000001815020</td>
                </tr>
            </tbody>
           </table>
           <select v-model="bankSelect" style="width: 100px; background-color: gray;">
                <!-- <option v-for="select in list" :value="select.code">{{select.name}}</option> -->
           </select>
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {
           bankSelect : '',
           bankCode : '',
           list : [
            {name: '농협은행', code: '011'},
            {name: '산업은행', code: '002'},
            {name: '기업은행', code: '003'},
            {name: '국민은행', code: '004'},
            {name: 'KEB하나은행', code: '081'},
            {name: '우리은행', code: '020'},
            {name: 'SC제일은행', code: '023'},
            {name: '시티은행', code: '027'},
            {name: '대구은행', code: '032'},
            {name: '광주은행', code: '034'},
            {name: '제주은행', code: '035'},
            {name: '전북은행', code: '037'},
            {name: '경남은행', code: '039'},
            {name: '새마을금고', code: '045'},
            {name: '신한은행', code: '088'},
            {name: '카카오뱅크', code: '090'},
           ]
        };
    },
    created() {
        
    },
    methods: {
        
    },
    components: { }
}
</script>


<style scoped>
table, tr, td, th{
    border: 1px solid black;
    margin: 1px;
    padding: 1px 5px;
}
.test{ display:flex; justify-content: center; align-items: center; height:100vh; }
.button{ width: 200px; height:40px; background-color:#ffffff; border:1px #a8a8a8 solid; color:black; display:flex; align-items: center; justify-content: center; cursor:pointer; }
</style>