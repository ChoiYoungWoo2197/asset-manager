<template>
  <!-- Modal -->
  <div class="modal fade" id="category-specification-update-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">카테고리규격 수정</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="categoryNameEdit">카테고리명<span class="text-danger"></span></label>
              <input type="text" class="form-control " id="categoryNameEdit" placeholder="" disabled readonly="readonly" :value="categoryName">
            </div>
            <div class="form-group">
              <label for="nameEdit">규격명<span class="text-danger">*</span></label>
              <input type="text" class="form-control " id="nameEdit" placeholder="" @keydown="keyDownNameInput" :value="name">
            </div>
            <div class="form-group">
              <div class="custom-control custom-radio d-inline pr-1">
                <input class="custom-control-input" type="radio" id="useYnFalseEditModal" name="useYnRadio" @click="clickRadioBtn(false)">
                <label for="useYnFalseEditModal" class="custom-control-label">비활성화</label>
              </div>
              <div class="custom-control custom-radio d-inline">
                <input class="custom-control-input" type="radio" id="useYnTrueEditModal" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                <label for="useYnTrueEditModal" class="custom-control-label">활성화</label>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button type="button" class="btn btn-primary" @click="updateBtnClick"> <i class="far fa-edit pr-1"></i>수정</button>
          <button type="button" class="btn btn-danger" @click="deleteBtnClick"><i class="far fa-trash-alt pr-1"></i>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import axios from "axios";

export default {
  name: "CategorySpecificationUpdate",
  props: {
    pCategory: Object,
  },
  data() {
    return {
      category : null,
      id : null,
      name : null,
      useYn : null,
      categoryId : null,
      categoryName : null,

    }
  },
  watch: {
    pCategory() {
      this.category = this.pCategory;
    }
  },
  methods: {
    setData(data) {
      console.log(data);
      this.id = data.id;
      this.name = data.name;
      this.categoryId = data.parentId;
      this.categoryName = data.parentName;
      this.useYn = data.useYn;

      if(this.useYn === true) {
        $('#category-specification-update-modal #useYnTrueEditModal').prop("checked", true);
      } else {
        $('#category-specification-update-modal #useYnFalseEditModal').prop("checked", true);
      }
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    updateBtnClick() {
      const vm = this;

      if($( 'input#nameEdit' ).val() ==="") {
        alert("이름을 입력해주세요.");
        return false;
      }

      axios.put('http://localhost:8080/api/category-specifications/' + this.id, {
        name : $( '#category-specification-update-modal input#nameEdit' ).val(),
        parentId : $('#category-specification-update-modal select#authorityEdit').val(),
        useYn : this.useYn,
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          // this.$router.push('Authority');
          vm.$emit("updateData", response.data);
          $('#category-specification-update-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    deleteBtnClick() {
      const vm = this;

      if(!confirm(this.name + "을 삭제하시겠습니까?")) {
        return false;
      }

      axios.delete('http://localhost:8080/api/category-specifications/' + this.id)
          .then(response => {
            // console.log(response);
            if(response.status === 200) {
              // this.$router.push('Authority');
              vm.$emit("updateData", response.data);
              $('#category-specification-update-modal').modal("hide");
            }
          }).catch(e => {
        alert(e);
      })
    },
  }

}
</script>

<style scoped>

</style>