$(function () {
  if ($(".scroll").length) {
    $(".scroll").jScrollPane();
  }

  $(".dash").on("click", function (e) {
    e.preventDefault();
    $(".slider").removeClass("open");
    $(".blur").removeClass("blur");
  });

  var scrHeight = $(window).height();
  $(".scrHeight").css("min-height", scrHeight);
});

$(window)
  .on("resize", function () {
    if ($(".scroll").length) {
      $(".scroll").jScrollPane();
    }
    var scrHeight = $(window).height();
    $(".scrHeight").css("min-height", scrHeight);
  })
  .on("load", function () {
    var scrHeight = $(window).height();
    $(".scrHeight").css("min-height", scrHeight);
  });

var clList = [".fadeIn, .fadeUp, .fadeDown"];

function animateClass() {
  setTimeout(function () {
    clList.forEach(function (item, index) {
      var anim = document.querySelectorAll(item);
      for (var i = 0, nbs = anim.length; i < nbs; i++) {
        var animELe = anim[i];
        var rect = animELe.getBoundingClientRect();
        var isVisible =
          rect.top - window.innerHeight < 0 && rect.bottom > -50 ? true : false;
        if (isVisible) {
          animELe.classList.add("animate");
        } else {
          animELe.classList.remove("animate");
        }
      }
    });
  }, 100);
}
function loader(el) {
  // var el = $(this);
  el.find("span").hide();
  el.find(".dot-flashing").fadeIn();
  setTimeout(function () {
    el.find(".dot-flashing").hide();
    el.find("span").fadeIn();
  }, 3000);
}
$(window)
  .on("load", function () {
    animateClass();
  })
  .on("scroll", function () {
    animateClass();
  });
animateClass();
