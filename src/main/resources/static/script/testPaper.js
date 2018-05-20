var newBuiltBtn = document.getElementsByClassName('new-built')[0];

function newBuiltTestPaper() {
  var addTestPagerDOM = document.getElementsByClassName('add-test-pager')[0],
      testPagerContentDOM = document.getElementsByClassName('content')[0];
  
  testPagerContentDOM ? testPagerContentDOM.style.display = 'none' : '';
  addTestPagerDOM ? addTestPagerDOM.style.display = 'block' : '';
}

if (newBuiltBtn) {
  newBuiltBtn.addEventListener("click", newBuiltTestPaper ,false);
}