<template>
  <div class="flex-container">


    <div class="wrapper">
      <h2>FileReader</h2>
      <img src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg" class="image-box" />
      <label for="file" class="upload-btn">
        <input  @click="onImageChange()" id="file" type="file" accept="image/*" />
        <span>Upload Image</span>
      </label>
    </div>

    <div class="wrapper">
      <h2>FileReader</h2>
      <img src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg" class="image-box" />
      <label for="file" class="upload-btn">
        <input  @click="onImageChange()" id="file" type="file" accept="image/*" />
        <span>Upload Image</span>
      </label>
    </div>

    <div class="wrapper">
      <h2>FileReader</h2>
      <img src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg" class="image-box" />
      <label for="file" class="upload-btn">
        <input  @click="onImageChange()" id="file" type="file" accept="image/*" />
        <span>Upload Image</span>
      </label>
    </div>

    <div class="wrapper">
      <h2>FileReader</h2>
      <img src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg" class="image-box" />
      <label for="file" class="upload-btn">
        <input  @change="onImageChange" id="file" type="file" accept="image/*" />
        <span>Upload Image</span>
      </label>
    </div>


  </div>
</template>

<script>
export default {
data() {
  return {
    files:[],//파일담
    previews:[],//미리보기 담
  };
},
created() {
 
},
watch: {

},
methods: {
  onImageChange(file){
    if (!file) return;
      const fileDOM = document.querySelector('#file'); //쿼리셀렉터로 file가져옴
      const previews = document.querySelectorAll('.image-box');//이미지 담을 박스

      
      const fileReader = new FileReader();//파일리더기 생성
      fileDOM.addEventListener('change', () => { //온체인지 이벤트 발생
          fileReader.onload = ({ target }) => {
            previews[i].src = target.result;
          };
          reader.readAsDataURL(fileDOM.files[i]);
      });
      const fileDOM2 = document.querySelector('#file2');
      fileDOM2.addEventListener('change', () => {
          const imageSrc = URL.createObjectURL(fileDOM2.files[0]);
          previews[1].src = imageSrc;
      });
    },
  }
}
</script>
<style scoped>
.flex-container {
  display: flex;
}

.wrapper {
  text-align: center;
  flex-grow: 1;
}  
.wrapper .image-box {
    width: 200px;
    height: 200px;
    object-fit: cover;
    display: block;
    margin: 20px auto;
  }

.wrapper .image-box .upload-btn {
    border: 1px solid #ddd;
    padding: 6px 12px;
    display: inline-block;
    cursor: pointer;
}
.wrapper .image-box .upload-btn input[type=file] {
  display: none;
}
</style>