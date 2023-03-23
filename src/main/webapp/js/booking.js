 // 날짜 정보
 const date = new Date();
            //console.log(date.getFullYear());
            const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
            const reserveDate = document.querySelector(".reserve-date");
            const inputReserveDate = document.querySelector('.reserveDate');
          
                const weekOfDay = ["일", "월", "화", "수", "목", "금", "토"]
                const year = date.getFullYear();
                const month = date.getMonth()+1;
                reserveDate.append(year + '/' + month);
                for (i = date.getDate(); i <= lastDay.getDate(); i++) {
    
                    const button = document.createElement("button");
                    const spanWeekOfDay = document.createElement("span");
                    const spanDay = document.createElement("span");
    
                    //class넣기
                    button.classList = "movie-date-wrapper";
                    spanWeekOfDay.classList = "movie-week-of-day";
                    spanDay.classList = "movie-day";
    
                    //weekOfDay[new Date(2020-03-날짜)]
                    const dayOfWeek = weekOfDay[new Date(year + "-" + month + "-" + i).getDay()];
    
                    //요일 넣기
                    if (dayOfWeek === "토") {
                        spanWeekOfDay.classList.add("saturday");
                        spanDay.classList.add("saturday");
                    } else if (dayOfWeek === "일") {
                        spanWeekOfDay.classList.add("sunday");
                        spanDay.classList.add("sunday");
                    }
                    spanWeekOfDay.innerHTML = dayOfWeek;
                    button.append(spanWeekOfDay);
                    //날짜 넣기
                    spanDay.innerHTML = i;
                    button.append(spanDay);
                    //button.append(i);
                    reserveDate.append(button);
    
                    dayClickEvent(button);
                } // for 끝 
    
            function dayClickEvent(button) {
                 button.addEventListener("click", function() {
                    const movieDateWrapperActive = document.querySelectorAll(".movie-date-wrapper-active");
                    movieDateWrapperActive.forEach((list) => {
                        list.classList.remove("movie-date-wrapper-active");
                    });
                    button.classList.add("movie-date-wrapper-active");
                    console.log(button.childNodes[1].innerHTML);
                    
                   inputReserveDate.value =
                    	year+'.'+month+'.'+button.childNodes[1].innerHTML+
                    	'('+ button.childNodes[0].innerHTML+')';
                   document.getElementsByName("movieDate")[0].value = inputReserveDate.value;
                    console.log(inputReserveDate.value);
                }) 
            } 
// 날짜 정보

