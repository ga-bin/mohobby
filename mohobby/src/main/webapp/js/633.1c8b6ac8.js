"use strict";(self["webpackChunkmohobby"]=self["webpackChunkmohobby"]||[]).push([[633],{21124:function(t,e,i){i.r(e),i.d(e,{default:function(){return F}});var s=function(){var t=this,e=t._self._c;return e("div",[e("RegisterForm")],1)},n=[],l=i(64562),a=i(34145),o=i(44127),r=(i(57658),i(36878)),h=i(17808),c=i(51767),u=i(75352);const d={...r.l,offsetY:!0,offsetOverflow:!0,transition:!1};var p=r.Z.extend({name:"v-autocomplete",props:{allowOverflow:{type:Boolean,default:!0},autoSelectFirst:{type:Boolean,default:!1},filter:{type:Function,default:(t,e,i)=>i.toLocaleLowerCase().indexOf(e.toLocaleLowerCase())>-1},hideNoData:Boolean,menuProps:{type:r.Z.options.props.menuProps.type,default:()=>d},noFilter:Boolean,searchInput:{type:String}},data(){return{lazySearch:this.searchInput}},computed:{classes(){return{...r.Z.options.computed.classes.call(this),"v-autocomplete":!0,"v-autocomplete--is-selecting-index":this.selectedIndex>-1}},computedItems(){return this.filteredItems},selectedValues(){return this.selectedItems.map((t=>this.getValue(t)))},hasDisplayedItems(){return this.hideSelected?this.filteredItems.some((t=>!this.hasItem(t))):this.filteredItems.length>0},currentRange(){return null==this.selectedItem?0:String(this.getText(this.selectedItem)).length},filteredItems(){return!this.isSearching||this.noFilter||null==this.internalSearch?this.allItems:this.allItems.filter((t=>{const e=(0,u.qF)(t,this.itemText),i=null!=e?String(e):"";return this.filter(t,String(this.internalSearch),i)}))},internalSearch:{get(){return this.lazySearch},set(t){this.lazySearch!==t&&(this.lazySearch=t,this.$emit("update:search-input",t))}},isAnyValueAllowed(){return!1},isDirty(){return this.searchIsDirty||this.selectedItems.length>0},isSearching(){return this.multiple&&this.searchIsDirty||this.searchIsDirty&&this.internalSearch!==this.getText(this.selectedItem)},menuCanShow(){return!!this.isFocused&&(this.hasDisplayedItems||!this.hideNoData)},$_menuProps(){const t=r.Z.options.computed.$_menuProps.call(this);return t.contentClass=`v-autocomplete__content ${t.contentClass||""}`.trim(),{...d,...t}},searchIsDirty(){return null!=this.internalSearch&&""!==this.internalSearch},selectedItem(){return this.multiple?null:this.selectedItems.find((t=>this.valueComparator(this.getValue(t),this.getValue(this.internalValue))))},listData(){const t=r.Z.options.computed.listData.call(this);return t.props={...t.props,items:this.virtualizedItems,noFilter:this.noFilter||!this.isSearching||!this.filteredItems.length,searchInput:this.internalSearch},t}},watch:{filteredItems:"onFilteredItemsChanged",internalValue:"setSearch",isFocused(t){t?(document.addEventListener("copy",this.onCopy),this.$refs.input&&this.$refs.input.select()):(document.removeEventListener("copy",this.onCopy),this.blur(),this.updateSelf())},isMenuActive(t){!t&&this.hasSlot&&(this.lazySearch=null)},items(t,e){e&&e.length||!this.hideNoData||!this.isFocused||this.isMenuActive||!t.length||this.activateMenu()},searchInput(t){this.lazySearch=t},internalSearch:"onInternalSearchChanged",itemText:"updateSelf"},created(){this.setSearch()},destroyed(){document.removeEventListener("copy",this.onCopy)},methods:{onFilteredItemsChanged(t,e){if(t!==e){if(!this.autoSelectFirst){const i=e[this.$refs.menu.listIndex];i?this.setMenuIndex(t.findIndex((t=>t===i))):this.setMenuIndex(-1),this.$emit("update:list-index",this.$refs.menu.listIndex)}this.$nextTick((()=>{this.internalSearch&&(1===t.length||this.autoSelectFirst)&&(this.$refs.menu.getTiles(),this.autoSelectFirst&&t.length&&(this.setMenuIndex(0),this.$emit("update:list-index",this.$refs.menu.listIndex)))}))}},onInternalSearchChanged(){this.updateMenuDimensions()},updateMenuDimensions(){this.isMenuActive&&this.$refs.menu&&this.$refs.menu.updateDimensions()},changeSelectedIndex(t){this.searchIsDirty||(this.multiple&&t===u.Do.left?-1===this.selectedIndex?this.selectedIndex=this.selectedItems.length-1:this.selectedIndex--:this.multiple&&t===u.Do.right?this.selectedIndex>=this.selectedItems.length-1?this.selectedIndex=-1:this.selectedIndex++:t!==u.Do.backspace&&t!==u.Do["delete"]||this.deleteCurrentItem())},deleteCurrentItem(){const t=this.selectedIndex,e=this.selectedItems[t];if(!this.isInteractive||this.getDisabled(e))return;const i=this.selectedItems.length-1;if(-1===this.selectedIndex&&0!==i)return void(this.selectedIndex=i);const s=this.selectedItems.length,n=t!==s-1?t:t-1,l=this.selectedItems[n];l?this.selectItem(e):this.setValue(this.multiple?[]:null),this.selectedIndex=n},clearableCallback(){this.internalSearch=null,r.Z.options.methods.clearableCallback.call(this)},genInput(){const t=h.Z.options.methods.genInput.call(this);return t.data=(0,c.ZP)(t.data,{attrs:{"aria-activedescendant":(0,u.vO)(this.$refs.menu,"activeTile.id"),autocomplete:(0,u.vO)(t.data,"attrs.autocomplete","off")},domProps:{value:this.internalSearch}}),t},genInputSlot(){const t=r.Z.options.methods.genInputSlot.call(this);return t.data.attrs.role="combobox",t},genSelections(){return this.hasSlot||this.multiple?r.Z.options.methods.genSelections.call(this):[]},onClick(t){this.isInteractive&&(this.selectedIndex>-1?this.selectedIndex=-1:this.onFocus(),this.isAppendInner(t.target)||this.activateMenu())},onInput(t){if(this.selectedIndex>-1||!t.target)return;const e=t.target,i=e.value;e.value&&this.activateMenu(),this.multiple||""!==i||this.deleteCurrentItem(),this.internalSearch=i,this.badInput=e.validity&&e.validity.badInput},onKeyDown(t){const e=t.keyCode;!t.ctrlKey&&[u.Do.home,u.Do.end].includes(e)||r.Z.options.methods.onKeyDown.call(this,t),this.changeSelectedIndex(e)},onSpaceDown(t){},onTabDown(t){r.Z.options.methods.onTabDown.call(this,t),this.updateSelf()},onUpDown(t){t.preventDefault(),this.activateMenu()},selectItem(t){r.Z.options.methods.selectItem.call(this,t),this.setSearch()},setSelectedItems(){r.Z.options.methods.setSelectedItems.call(this),this.isFocused||this.setSearch()},setSearch(){this.$nextTick((()=>{this.multiple&&this.internalSearch&&this.isMenuActive||(this.internalSearch=!this.selectedItems.length||this.multiple||this.hasSlot?null:this.getText(this.selectedItem))}))},updateSelf(){(this.searchIsDirty||this.internalValue)&&(this.multiple||this.valueComparator(this.internalSearch,this.getValue(this.internalValue))||this.setSearch())},hasItem(t){return this.selectedValues.indexOf(this.getValue(t))>-1},onCopy(t){var e,i;if(-1===this.selectedIndex)return;const s=this.selectedItems[this.selectedIndex],n=this.getText(s);null===(e=t.clipboardData)||void 0===e||e.setData("text/plain",n),null===(i=t.clipboardData)||void 0===i||i.setData("text/vnd.vuetify.autocomplete.item+plain",n),t.preventDefault()}}}),m=p.extend({name:"v-combobox",props:{delimiters:{type:Array,default:()=>[]},returnObject:{type:Boolean,default:!0}},data:()=>({editingIndex:-1}),computed:{computedCounterValue(){return this.multiple?this.selectedItems.length:(this.internalSearch||"").toString().length},hasSlot(){return r.Z.options.computed.hasSlot.call(this)||this.multiple},isAnyValueAllowed(){return!0},menuCanShow(){return!!this.isFocused&&(this.hasDisplayedItems||!!this.$slots["no-data"]&&!this.hideNoData)},searchIsDirty(){return null!=this.internalSearch}},methods:{onInternalSearchChanged(t){if(t&&this.multiple&&this.delimiters.length){const e=this.delimiters.find((e=>t.endsWith(e)));null!=e&&(this.internalSearch=t.slice(0,t.length-e.length),this.updateTags())}this.updateMenuDimensions()},genInput(){const t=p.options.methods.genInput.call(this);return delete t.data.attrs.name,t.data.on.paste=this.onPaste,t},genChipSelection(t,e){const i=r.Z.options.methods.genChipSelection.call(this,t,e);return this.multiple&&(i.componentOptions.listeners={...i.componentOptions.listeners,dblclick:()=>{this.editingIndex=e,this.internalSearch=this.getText(t),this.selectedIndex=-1}}),i},onChipInput(t){r.Z.options.methods.onChipInput.call(this,t),this.editingIndex=-1},onEnterDown(t){t.preventDefault(),this.getMenuIndex()>-1||this.$nextTick(this.updateSelf)},onKeyDown(t){const e=t.keyCode;!t.ctrlKey&&[u.Do.home,u.Do.end].includes(e)||r.Z.options.methods.onKeyDown.call(this,t),this.multiple&&e===u.Do.left&&0===this.$refs.input.selectionStart?this.updateSelf():e===u.Do.enter&&this.onEnterDown(t),this.changeSelectedIndex(e)},onTabDown(t){if(this.multiple&&this.internalSearch&&-1===this.getMenuIndex())return t.preventDefault(),t.stopPropagation(),this.updateTags();p.options.methods.onTabDown.call(this,t)},selectItem(t){this.editingIndex>-1?this.updateEditing():(p.options.methods.selectItem.call(this,t),this.internalSearch&&this.multiple&&this.getText(t).toLocaleLowerCase().includes(this.internalSearch.toLocaleLowerCase())&&(this.internalSearch=null))},setSelectedItems(){null==this.internalValue||""===this.internalValue?this.selectedItems=[]:this.selectedItems=this.multiple?this.internalValue:[this.internalValue]},setValue(t){r.Z.options.methods.setValue.call(this,void 0===t?this.internalSearch:t)},updateEditing(){const t=this.internalValue.slice(),e=this.selectedItems.findIndex((t=>this.getText(t)===this.internalSearch));if(e>-1){const i="object"===typeof t[e]?Object.assign({},t[e]):t[e];t.splice(e,1),t.push(i)}else t[this.editingIndex]=this.internalSearch;this.setValue(t),this.editingIndex=-1,this.internalSearch=null},updateCombobox(){if(!this.searchIsDirty)return;this.internalSearch!==this.getText(this.internalValue)&&this.setValue();const t=Boolean(this.$scopedSlots.selection)||this.hasChips;t&&(this.internalSearch=null)},updateSelf(){this.multiple?this.updateTags():this.updateCombobox()},updateTags(){const t=this.getMenuIndex();if(t<0&&!this.searchIsDirty||!this.internalSearch)return;if(this.editingIndex>-1)return this.updateEditing();const e=this.selectedItems.findIndex((t=>this.internalSearch===this.getText(t))),i=e>-1&&"object"===typeof this.selectedItems[e]?Object.assign({},this.selectedItems[e]):this.internalSearch;if(e>-1){const t=this.internalValue.slice();t.splice(e,1),this.setValue(t)}if(t>-1)return this.internalSearch=null;this.selectItem(i),this.internalSearch=null},onPaste(t){var e;if(!this.multiple||this.searchIsDirty)return;const i=null===(e=t.clipboardData)||void 0===e?void 0:e.getData("text/vnd.vuetify.autocomplete.item+plain");i&&-1===this.findExistingIndex(i)&&(t.preventDefault(),r.Z.options.methods.selectItem.call(this,i))},clearableCallback(){this.editingIndex=-1,p.options.methods.clearableCallback.call(this)}}}),g=i(69256),f=i(31625),I=i(4324),v=i(65495),y=i(54525),x=i(45187),S=i(13687),b=i(92648),C=function(){var t=this,e=t._self._c;return e("div",{staticClass:"mx-auto",staticStyle:{width:"1050px",height:"500px"}},[e(a.Z,{attrs:{outlined:""}},[e("div",{staticClass:"plus_btn",attrs:{rounded:""},on:{click:function(e){return t.addFileList(t.i)}}},[e(I.Z,{attrs:{color:"#2255b1",size:"30"}},[t._v("mdi-plus")]),e(I.Z,{attrs:{color:"#2255b1",size:"30"}},[t._v("mdi-camera")])],1),e("div",{staticClass:"room-file-upload-example"},[e("div",{staticClass:"room-file-notice-item room-file-notice-item-red"},[t._v(" 타인에게 불쾌감을 줄 수 있는 게시글은 비공개 처리가 될 수 있으니 유의 부탁드립니다🙏 ")])]),e("form",{attrs:{id:"feedInsert",name:"feedInsert"},on:{submit:function(t){t.preventDefault()}}},[e(g.Z,{attrs:{fluid:""}},[e("input",{directives:[{name:"model",rawName:"v-model",value:t.memberId,expression:"memberId"}],attrs:{type:"hidden",name:"memberId"},domProps:{value:t.memberId},on:{input:function(e){e.target.composing||(t.memberId=e.target.value)}}}),e("input",{directives:[{name:"model",rawName:"v-model",value:t.hashtag,expression:"hashtag"}],attrs:{type:"hidden",name:"hashtag"},domProps:{value:t.hashtag},on:{input:function(e){e.target.composing||(t.hashtag=e.target.value)}}}),e("div",{staticClass:"main-container"},[e("div",{staticClass:"room-deal-information-container"},[e("div",{staticClass:"room-file-upload-example-container"},t._l(t.fileList,(function(i,s){return e("div",{key:s},[e(f.Z,{staticClass:"mx-auto",staticStyle:{"margin-top":"20px"},attrs:{label:"이곳을 눌러 사진을 첨부해주세요🙏",type:"file",id:"file_input",name:"fileList","prepend-icon":"mdi-plus",dense:"",accept:"image/png, image/jpeg, image/jpg"},on:{change:function(e){return t.onImageChange(s)}},model:{value:i.file,callback:function(e){t.$set(i,"file",e)},expression:"file.file"}}),e("div",{staticClass:"img_wrap"},[e("div",{staticClass:"img_box"},[e(v.Z,{attrs:{src:i.url,"aspect-ratio":"4/3",height:"200",width:"200","lazy-src":"",error:""}}),e("div",{staticClass:"del_btn",attrs:{rounded:""},on:{click:function(e){return t.delFileList(s)}}},[e(I.Z,{staticClass:"del_icon"},[t._v("mdi-delete")])],1)],1)])],1)})),0),t.fileList[0].file?t._e():e("div",{staticClass:"room-file-notice-item"},[t._v(" 사진은 한 장 이상 등록 부탁드립니다🙏 ")])])]),e(b.Z,{attrs:{name:"content","auto-grow":"",placeholder:"내용을 입력해주세요!",value:"",counter:"2000"},on:{change:function(e){return t.checkContent(t.content)}},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}})],1),e(g.Z,{attrs:{fluid:""}},[e(m,{attrs:{"hide-no-data":!t.search,items:t.items,"search-input":t.search,"hide-selected":"",label:"해시태그를 등록해보세요!",multiple:"","small-chips":"",solo:""},on:{"update:searchInput":function(e){t.search=e},"update:search-input":function(e){t.search=e}},scopedSlots:t._u([{key:"no-data",fn:function(){return[e(y.Z,[e("span",{staticClass:"subheading"},[t._v("Create")]),e(o.Z,{attrs:{color:`${t.colors[t.nonce-1]} lighten-3`,label:"",small:""}},[t._v(" "+t._s(t.search)+" ")])],1)]},proxy:!0},{key:"selection",fn:function({attrs:i,item:s,parent:n,selected:l}){return[s===Object(s)?e(o.Z,t._b({attrs:{color:`${s.color} lighten-3`,"input-value":l,label:"",small:""}},"v-chip",i,!1),[e("span",{staticClass:"pr-2"},[t._v(" "+t._s(s.text)+" ")]),e(I.Z,{attrs:{small:""},on:{click:function(t){return n.selectItem(s)}}},[t._v(" $delete ")])],1):t._e()]}},{key:"item",fn:function({index:i,item:s}){return[t.editing===s?e(h.Z,{attrs:{autofocus:"",flat:"","background-color":"transparent","hide-details":"",solo:""},on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.edit(i,s)}},model:{value:t.editing.text,callback:function(e){t.$set(t.editing,"text",e)},expression:"editing.text"}}):e(o.Z,{attrs:{color:`${s.color} lighten-3`,dark:"",label:"",small:""}},[t._v(" "+t._s(s.text)+" ")]),e(S.Z),e(x.Z,{on:{click:function(t){t.stopPropagation()}}},[e(l.Z,{attrs:{icon:""},on:{click:function(e){return e.stopPropagation(),e.preventDefault(),t.edit(i,s)}}},[e(I.Z,[t._v(t._s(t.editing!==s?"mdi-pencil":"mdi-check"))])],1)],1)]}}]),model:{value:t.model,callback:function(e){t.model=e},expression:"model"}})],1)],1)]),e(l.Z,{staticClass:"white--text",attrs:{rounded:"",color:"#2ac187"},on:{click:t.uploadImage}},[t._v("가보자고")])],1)},D=[],w={data(){return{activator:null,attach:null,colors:["green","purple","indigo","cyan","teal","orange","darkgrey"],editing:null,editingIndex:-1,items:[{header:"최근 인기있는 해시태그를 추가해보세요!"}],nonce:1,menu:!1,model:[{text:"취미",color:"blue"}],search:null,x:0,y:0,uploadimageurl:[],imagecnt:0,fileList:[{file:"",url:""}],file:{},postId:"1",formData:{},fListName:[],addFName:"",memberId:this.$store.state.id,content:"",getHashtag:[],hashtag:""}},created(){this.getHobbies()},watch:{model(t,e){t.length!==e.length&&(this.model=t.map((t=>("string"===typeof t&&(t={text:t,color:this.colors[this.nonce-1]},this.items.push(t),this.nonce++),t))))}},methods:{getHobbies(){let t=["스포츠/레저","어학/외국어","문화/예술","여행/캠핑","음악","건강/다이어트","친목/모임","맛집/요리","인문/과학","팬클럽","게임","만화/애니메이션","반려동물/동물","교육/공부","IT/컴퓨터","경제/재테크","종교/봉사","자연/귀농"];for(let e=0;e<t.length;e++){let i,s,n=this.colors.length-1;s=e>n?this.colors[e%n]:this.colors[e],i=t[e],this.items.push({text:i,color:s})}},onImageChange(t){let e=this.fileList[t].file;if(!e)return;console.log("file------\x3e"),console.log(e);for(let s=0;s<this.fileList.length;s++){let t=this.fileList[s].file.name;this.fListName.push(t)}if(console.log(this.fListName),this.fileList.length>1&&-1!==this.fListName.indexOf(e.name))return this.$swal("중복되는 파일은 등록이 불가합니다🙏"),this.file="",void(this.fListName=[]);e.type.match("image/.*")||this.$swal("png, jpg, jpeg파일만 부탁드립니다🙏");const i=new FileReader;console.log("file.name: "+e.name),i.onload=e=>{this.fileList[t].url=e.target.result},i.readAsDataURL(e)},addFileList(t){let e=this.fileList,i=e.length;console.log(e),i>0&&""==e[i-1].file?this.$swal("사진이 첨부되지 않은 추가칸이 있는지 확인 부탁드립니다🙏"):this.fileList.length>4?this.$swal("사진은 5장까지만 추가 부탁드립니다🙏"):this.fileList.push({file:"",url:""})},delFileList(t){1==this.fileList.length?(this.fileList[0].file="",this.fileList[0].url=""):this.fileList.splice(t,1)},edit(t,e){this.editing?(this.editing=null,this.editingIndex=-1):(this.editing=e,this.editingIndex=t)},uploadImage(){let t=this.fileList,e=t.length;if(this.content.length>2e3)return void this.$swal("내용은 2000자 이내로 부탁드립니다🙏");if(e=""==t[0].file)return void this.$swal("사진은 최소 한 장 이상 등록 부탁드립니다🙏");if(e>1&&""==t[e-1].file||""==t[e-1].url)return void this.$swal("빈 사진추가 칸은 삭제 혹은 사진첨부 부탁드립니다🙏");let i=this;console.log(i.fileList),this.model.forEach((t=>{this.getHashtag.push(t.text)}));const s=this.getHashtag.join();feedInsert.hashtag.value=s;const n=new FormData(feedInsert);this.axios.post("/sns/myfeed",n,{headers:{"Content-Type":"multipart/form-data"}}).then((function(t){i.$router.push({path:"/snsUserFeed",query:{userId:i.memberId}})})).catch((function(t){console.log(t)}))}}},$=w,Z=i(1001),k=(0,Z.Z)($,C,D,!1,null,"42f7ba2c",null),V=k.exports,L={name:"snsFeedRegister",components:{RegisterForm:V},data(){return{}},methods:{}},_=L,T=(0,Z.Z)(_,s,n,!1,null,null,null),F=T.exports},4245:function(){},34145:function(t,e,i){i(4245);var s=i(83434),n=i(88126),l=i(58860),a=i(67678);e["Z"]=(0,a.Z)(n.Z,l.Z,s.Z).extend({name:"v-card",props:{flat:Boolean,hover:Boolean,img:String,link:Boolean,loaderHeight:{type:[Number,String],default:4},raised:Boolean},computed:{classes(){return{"v-card":!0,...l.Z.options.computed.classes.call(this),"v-card--flat":this.flat,"v-card--hover":this.hover,"v-card--link":this.isClickable,"v-card--loading":this.loading,"v-card--disabled":this.disabled,"v-card--raised":this.raised,...s.Z.options.computed.classes.call(this)}},styles(){const t={...s.Z.options.computed.styles.call(this)};return this.img&&(t.background=`url("${this.img}") center center / cover no-repeat`),t}},methods:{genProgress(){const t=n.Z.options.methods.genProgress.call(this);return t?this.$createElement("div",{staticClass:"v-card__progress",key:"progress"},[t]):null}},render(t){const{tag:e,data:i}=this.generateRouteLink();return i.style=this.styles,this.isClickable&&(i.attrs=i.attrs||{},i.attrs.tabindex=0),t(e,this.setBackgroundColor(this.color,i),[this.genProgress(),this.$slots.default])}})},31625:function(t,e,i){i.d(e,{Z:function(){return h}});i(57658);var s=i(17808),n=s.Z,l=i(44127),a=i(75352),o=i(74101),r=i(51767),h=n.extend({name:"v-file-input",model:{prop:"value",event:"change"},props:{chips:Boolean,clearable:{type:Boolean,default:!0},counterSizeString:{type:String,default:"$vuetify.fileInput.counterSize"},counterString:{type:String,default:"$vuetify.fileInput.counter"},hideInput:Boolean,multiple:Boolean,placeholder:String,prependIcon:{type:String,default:"$file"},readonly:{type:Boolean,default:!1},showSize:{type:[Boolean,Number],default:!1,validator:t=>"boolean"===typeof t||[1e3,1024].includes(t)},smallChips:Boolean,truncateLength:{type:[Number,String],default:22},type:{type:String,default:"file"},value:{default:void 0,validator:t=>(0,a.TI)(t).every((t=>null!=t&&"object"===typeof t))}},computed:{classes(){return{...n.options.computed.classes.call(this),"v-file-input":!0}},computedCounterValue(){const t=this.multiple&&this.lazyValue?this.lazyValue.length:this.lazyValue instanceof File?1:0;if(!this.showSize)return this.$vuetify.lang.t(this.counterString,t);const e=this.internalArrayValue.reduce(((t,{size:e=0})=>t+e),0);return this.$vuetify.lang.t(this.counterSizeString,t,(0,a.XE)(e,1024===this.base))},internalArrayValue(){return(0,a.TI)(this.internalValue)},internalValue:{get(){return this.lazyValue},set(t){this.lazyValue=t,this.$emit("change",this.lazyValue)}},isDirty(){return this.internalArrayValue.length>0},isLabelActive(){return this.isDirty},text(){return this.isDirty||!this.persistentPlaceholder&&!this.isFocused&&this.hasLabel?this.internalArrayValue.map((t=>{const{name:e="",size:i=0}=t,s=this.truncateText(e);return this.showSize?`${s} (${(0,a.XE)(i,1024===this.base)})`:s})):[this.placeholder]},base(){return"boolean"!==typeof this.showSize?this.showSize:void 0},hasChips(){return this.chips||this.smallChips}},watch:{readonly:{handler(t){!0===t&&(0,o.N6)("readonly is not supported on <v-file-input>",this)},immediate:!0},value(t){const e=this.multiple?t:t?[t]:[];(0,a.vZ)(e,this.$refs.input.files)||(this.$refs.input.value="")}},methods:{clearableCallback(){this.internalValue=this.multiple?[]:null,this.$refs.input.value=""},genChips(){return this.isDirty?this.text.map(((t,e)=>this.$createElement(l.Z,{props:{small:this.smallChips},on:{"click:close":()=>{const t=this.internalValue;t.splice(e,1),this.internalValue=t}}},[t]))):[]},genControl(){const t=n.options.methods.genControl.call(this);return this.hideInput&&(t.data.style=(0,r.y0)(t.data.style,{display:"none"})),t},genInput(){const t=n.options.methods.genInput.call(this);return t.data.attrs.multiple=this.multiple,delete t.data.domProps.value,delete t.data.on.input,t.data.on.change=this.onInput,[this.genSelections(),t]},genPrependSlot(){if(!this.prependIcon)return null;const t=this.genIcon("prepend",(()=>{this.$refs.input.click()}));return this.genSlot("prepend","outer",[t])},genSelectionText(){const t=this.text.length;return t<2?this.text:this.showSize&&!this.counter?[this.computedCounterValue]:[this.$vuetify.lang.t(this.counterString,t)]},genSelections(){const t=[];return this.isDirty&&this.$scopedSlots.selection?this.internalArrayValue.forEach(((e,i)=>{this.$scopedSlots.selection&&t.push(this.$scopedSlots.selection({text:this.text[i],file:e,index:i}))})):t.push(this.hasChips&&this.isDirty?this.genChips():this.genSelectionText()),this.$createElement("div",{staticClass:"v-file-input__text",class:{"v-file-input__text--placeholder":this.placeholder&&!this.isDirty,"v-file-input__text--chips":this.hasChips&&!this.$scopedSlots.selection}},t)},genTextFieldSlot(){const t=n.options.methods.genTextFieldSlot.call(this);return t.data.on={...t.data.on||{},click:()=>this.$refs.input.click()},t},onInput(t){const e=[...t.target.files||[]];this.internalValue=this.multiple?e:e[0],this.initialValue=this.internalValue},onKeyDown(t){this.$emit("keydown",t)},truncateText(t){if(t.length<Number(this.truncateLength))return t;const e=Math.floor((Number(this.truncateLength)-1)/2);return`${t.slice(0,e)}…${t.slice(t.length-e)}`}}})},92648:function(t,e,i){i.d(e,{Z:function(){return a}});var s=i(17808),n=i(67678);const l=(0,n.Z)(s.Z);var a=l.extend({name:"v-textarea",props:{autoGrow:Boolean,noResize:Boolean,rowHeight:{type:[Number,String],default:24,validator:t=>!isNaN(parseFloat(t))},rows:{type:[Number,String],default:5,validator:t=>!isNaN(parseInt(t,10))}},computed:{classes(){return{"v-textarea":!0,"v-textarea--auto-grow":this.autoGrow,"v-textarea--no-resize":this.noResizeHandle,...s.Z.options.computed.classes.call(this)}},noResizeHandle(){return this.noResize||this.autoGrow}},watch:{autoGrow(t){this.$nextTick((()=>{var e;t?this.calculateInputHeight():null===(e=this.$refs.input)||void 0===e||e.style.removeProperty("height")}))},lazyValue(){this.autoGrow&&this.$nextTick(this.calculateInputHeight)},rowHeight(){this.autoGrow&&this.$nextTick(this.calculateInputHeight)}},mounted(){setTimeout((()=>{this.autoGrow&&this.calculateInputHeight()}),0)},methods:{calculateInputHeight(){const t=this.$refs.input;if(!t)return;t.style.height="0";const e=t.scrollHeight,i=parseInt(this.rows,10)*parseFloat(this.rowHeight);t.style.height=Math.max(i,e)+"px"},genInput(){const t=s.Z.options.methods.genInput.call(this);return t.tag="textarea",delete t.data.attrs.type,t.data.attrs.rows=this.rows,t},onInput(t){s.Z.options.methods.onInput.call(this,t),this.autoGrow&&this.calculateInputHeight()},onKeyDown(t){this.isFocused&&13===t.keyCode&&t.stopPropagation(),this.$emit("keydown",t)}}})}}]);
//# sourceMappingURL=633.1c8b6ac8.js.map