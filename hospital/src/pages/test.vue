
<template>
  <div>
    <div class="cropper-content">
      <div class="cropper">
        <vueCropper
          ref="cropper"
          :img="option.img"
          :outputSize="option.size"
          :outputType="option.outputType"
          :info="true"
          :full="option.full"
          :canMove="option.canMove"
          :canMoveBox="option.canMoveBox"
          :original="option.original"
          :autoCrop="option.autoCrop"
          :autoCropWidth="option.autoCropWidth"
          :autoCropHeight="option.autoCropHeight"
          :fixedBox="option.fixedBox"
          @realTime="realTime"
          @imgLoad="imgLoad">
        </vueCropper>
      </div>
      <div class="show-preview" :style="{'width': previews.w + 'px', 'height': previews.h + 'px',  'overflow': 'hidden', 'margin': '5px'}">
        <div :style="previews.div" class="preview">
          <img :src="previews.url" :style="previews.img">
        </div>
      </div>
    </div>
    <div class="footer-btn">
      <div class="scope-btn">
        <label class="btn" for="uploads">更换图片</label>
        <input type="file" id="uploads" style="position:absolute; clip:rect(0 0 0 0);" accept="image/png, image/jpeg, image/gif, image/jpg" @change="uploadImg($event, 1)">

        <!--<el-button type="primary" @click="startCrop">开始截图</el-button>-->
        <!--<el-button type="primary" @click="getData">获取截图数据</el-button>-->

        <button  @click="changeScale(1)">+</button>
        <button  @click="changeScale(-1)">-</button>
        <button  @click="rotateLeft">↺</button>
        <button  @click="rotateRight">↻</button>
      </div>
      <div class="upload-btn">
        <button  @click="down('blob')">下载</button>
      </div>
    </div>
    <Cut :img-src="imgSrc"></Cut>

  </div>
</template>
<script>
  import VueCropper from 'vue-cropper'

  import Cut from '../pages/cut'
  export default {
    name: "test.vue",
    components: {
      Cut
    },
    data() {
      return {
        crap: false,
        previews: {},
        option: {
          img: 'https://bpic.588ku.com/element_water_img/18/06/12/b2887846cb19ff36a5502401ac918809.jpg',
          size: 1,
          full: false,
          outputType: 'png',
          canMove: true,
          original: false,
          canMoveBox: true,
          autoCrop: true,
         // autoCropWidth: 200,
         // autoCropHeight: 200,
          fixedBox: false,
          infoTrue: true, // 为展示真实输出图片宽高 false 展示看到的截图框宽
          centerBox: true, // 截图框是否被限制在图片里面
          downImg: '#'
        },
        imgSrc: 'http://10.145.8.158:8082/documentscan/scanImage/CS/P2020031600030/210A0048507/CS110/grgbanking_mtn20200514162518001.png'
      }
    },
    methods: {
      changeScale (num) {
        num = num || 1
        this.$refs.cropper.changeScale(num)
      },
      rotateLeft () {
        let b=this.$refs.cropper.getImgAxis();
        console.log(b)
        let a=this.$refs.cropper.getCropAxis();
        console.log(a)
        this.$refs.cropper.rotateLeft()
      },
      rotateRight () {
        this.$refs.cropper.rotateRight()
      },
      finish (type) {  // 输出
        var test = window.open('about:blank')
        test.document.body.innerHTML = '图片生成中..'
        if (type === 'blob') {
          this.$refs.cropper.getCropBlob((data) => {
            var img = window.URL.createObjectURL(data)
            this.model = true
            this.modelSrc = img
          })
        } else {
          this.$refs.cropper.getCropData((data) => {
            this.model = true
            this.modelSrc = data
          })
        }
      },
      realTime (data) {  // 实时预览函数
        this.previews = data
      },
      down (type) {
        // event.preventDefault()
        var aLink = document.createElement('a')
        aLink.download = 'author-img' // 输出
        if (type === 'blob') {
          this.$refs.cropper.getCropBlob((data) => {
            console.log(data)
            this.downImg = window.URL.createObjectURL(data)
            // aLink.download = this.downImg;
            console.log(this.downImg)
            aLink.href = window.URL.createObjectURL(data)
            aLink.click()
          })
          console.log(aLink)
        } else {
          this.$refs.cropper.getCropData((data) => {
            this.downImg = data
            aLink.href = data
            aLink.click()
          })
        }
      },
      uploadImg (e, num) { //上传图片
        this.option.img
        var file = e.target.files[0]
        if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
          alert('图片类型必须是.gif,jpeg,jpg,png,bmp中的一种')
          return false
        };
        var reader = new FileReader()
        reader.onload = (e) => {
          let data
          if (typeof e.target.result === 'object') { // 把Array Buffer转化为blob 如果是base64不需要
            data = window.URL.createObjectURL(new Blob([e.target.result]))
          } else {
            data = e.target.result
          }
          if (num === 1) {
            this.option.img = data
          } else if (num === 2) {
            this.example2.img = data
          }
        } // 转化为base64
        reader.readAsDataURL(file) // 转化为blob reader.readAsArrayBuffer(file)
      },
      imgLoad (msg) {
        console.log(msg)
      }
    }
  }
</script>
<style scoped>
  .cropper-content{
    width: 600px;
    height: 600px;
  }
  .cropper{
    width: 300px;
    height: 300px;
  }
</style>
