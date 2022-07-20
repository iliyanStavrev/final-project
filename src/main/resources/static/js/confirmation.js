
if (window.location.href.split('?')[1] === 'isAlreadyTaken=true'){
    alert('You already have this training');
    window.location.href =  window.location.href.split('?')[0]
}
