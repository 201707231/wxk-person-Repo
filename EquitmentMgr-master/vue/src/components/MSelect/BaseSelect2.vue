<script>
import { Select } from 'element-ui'

export default {
  extends: Select,
  name: 'BaseSelect2',
  data() {
    return {
      list:[]
    }
  },
  props: {
    initUrl: String,
    mKey:String,
    mLabel:String
  },
  created(){
    this.getData()
  },
  methods: {
    getData() {
      console.log("BaseSelect2  Get Data");
      this.isLoading = true
      this.api({
        url: this.initUrl,
        method: 'get',
      }).then(data => {
        console.log("baseSelect2 Ret Data:", data)
        console.log(this.mKey, this.mLabel)
        if (this.mKey && this.mLabel) {
          this.list = data.list.map(x => { x.key = x[this.mKey]; x.label = x[this.mLabel]; return x; })
        }
        else {
          this.list = data.list
        }
        this.isLoading = false
      })
    },
  }
}

</script>
