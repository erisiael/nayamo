/**
 * 
 */
	//var w = 900;
	//var h = 600;
	var w = screen.width;
	var h = screen.height;
	/*var w = window.outerWidth;
	var h = window.outerHeight;*/
	console.log(w);
	console.log(h);
	
	var circlefill = "rgba(118,214,255,0.7)";
	
	var rootfill = "rgba(255,119,142,1)",
		rootstroke = "rgba(204,198,65,0.5) !important",
    	rootstroke_width = "10 !important",
    	rootstroke_dasharray = "0 !important;";
    	
    var	linestroke = "black",
    	linestroke_width = "1",
    	lineshape_rendering = "crispEdges",
    	lineopacity = "0.1";
    
    var textfill = "black",
    	textfont_size = "20px",
		textfont_family = "맑은 고딕",
		textfont = "bolder";
	
	var zoomrate = [1/8, 8];
	
	var selected = null;
	var selectednode = null;
	
	var node = [
		            //{text : "1번타자", parent : 1, child : [1,2,3], values: 50}
		            {text : "루트 노드", root : true, values : 100} // 처음 시작할 때, 기본적으로 실행
		       ]
	var links = [];
	
	setXY();
	linkNode(); // links라는 배열을 채워주는 역할. 

	var svg = d3.select("#main").append("svg")//d3는 라이브러리. 여기까지 d3에서 #main을 선택하고, svg 태그를 만든 다음에 선택한 상태
							.attr("width", w)  //append를 통해서 var svg에 svg 태그를 선택하고 있다. 그 후 attr을 이용해 실행하고 있다.
							.attr("height", h);  

					
	var transform = d3.zoomIdentity;

	var force = d3.layout.force()//force()는 선에 대한 정보를 
						.nodes(node)
					    .links(links)
					    .size([w,h]); // force를 이용해 선에 대한 정보를 node와 비교하면, 자동으로 연결을 해준다. 원리는 몰라
	
    var width = svg
							.attr("width");
	var	height = svg
							.attr("height");
	var	addg = svg	// var svg에 담긴 svg로 실행.
							.selectAll("g") // g라는 태그는 그룹화하는 태그. "생각"과 text를 묶어주기 위해서 쓴다.
							.data(node) // node가 model에 속한다.model 안에는 데이터가 담겨 있다. selectAll을 실행하기 전에 data(node)에 앞으로 어떤 데이터를 참조하겠다는 정보가 담겨있다. data안에 있는 node의 개수? 만큼 참조하겠다는 소리. node라는 변수라는 곳에 text,value...을 data()메소드가 만들어준다. 
							.enter()
						//뒤에서 부터 읽어 온다.
							.append("g") // g를 가져오는데, data(node) 안에 있는 data만큼 만든다.
							.attr("transform", "translate(0,0)");
	
	var g = svg.selectAll("g"); // svg 태그 안의 g태그를 가져와서 var g 안에 넣는다.
	
	var rect = g.append("circle") 													//원을 만드는 변수 .attr은 실행하는것을 뜻한다. 원을 만드는 변수. circle이라는 태그를 만든다. g는 모든 g를 뜻한다. circle을 append했으므로 circle에 선택자가 있다. 
										.attr("r", function(d) { return d.values; })//circle라는 속성에 r을추가 (ex.    <circle r="function(d){return d.values;}"> d는 node안에 있는 배열안에 있는 것            )
										.attr("id", function(d) { if(d.root) {return "circle-root";} })
										.attr("cx", function(d) { return d.x; })
    									.attr("cy", function(d) { return d.y; });
										// attr은 circle에 대한 속성들이다. <circle 이 곳에 속성을 쓰는 것과 같은 원리></circle>
										// attr("~")처럼 하나의 속성이 있으면 "~"에 해당하는 값을 가져와라.
										// attr("~", string 혹은 function) 는 "~"에 두 번째 function 혹은 string 값을 set 해 주겠다는 소리.
										// d란, node 전체 배열 하나의 컬럼을 말한다.
										//  call-back 함수란, 어떤 function의 return의 결과를 인수로 넘겨주는 함수
			
		
	var line = g.selectAll("line")
										.data(links) // 
										.enter()
										.append("line")
										.style("pointer-events", "none");
	
	var text = g.append("text") // g라는 태그 전체에 text를 append해라. 지금 이 상태는 <g><circle><text></circle></text></g>가 된 상태.
			.attr("dx", function(d) { return (d.x - (d.values / 3)); })
			.attr("dy", function(d) { return (d.y - (d.values / 4)); })
			.text(function(d, i) { return d.text; });
		  				
	var drag = d3.behavior.drag()
											    .origin(function(d) { return d; })
											    .on("dragstart", dragstarted) // dragstart를 했을 때, dragstarted를 실행해라. listener를 달아준 것이다
											    .on("drag", dragged)
											    .on("dragend", dragended);
	
	
	var zoom = d3.behavior.zoom()
											    .scaleExtent(zoomrate)
											    .on("zoom", zoomed); // zoom이라는 행위에 대한, zoomed를 실행한다. zoomed에 대한 listener를 달아준 것.
	
	force.on("tick", tick);
	svg.call(zoom);
	rect.call(drag);
    rect.on("click", clicked);
    styleData();	// 저장 후 불러오기를 할 때, 동일한 css를 위해 만든 함수. 그리고 모든 "생각"들에 대한 동일한 css를 유지하기 위한 함수. 그리고 수정을 했을 경우 style 태그 자체에 저장하기 위한 함수.
	
	function clicked(d) {	// d라는 변수(= 노드 안의 정보)
		if (selected != d3.select(this)) {
			if (selected != undefined) {
				selected.classed("selected", false);
			}
			selected = d3.select(this);
			selectednode = d;
			d3.select(this).classed("selected", true);
			
			d3.selectAll(".selected-border").remove();
			d3.select(this.parentNode).append("circle")
	        .classed("selected-border", true)
	        .attr("cx", function(d) { return d.x; })
	        .attr("cy", function(d) { return d.y; })
	        .attr("r", function(d) { return (d.values + 50); }) // 원래 크기보다 50을 크게 만들고,//transition과 duration으로 딜레이를준다
	        .transition()
			.duration(750) // 0.75초 동안 줄여라.
			.attr("r", function(d) { return d.values; })
			.style("stroke-width", 0)
			.style("stroke-opacity", .2);
		}
		
		/*
	     * call from jongchan.js
	     */
	    renewFooter(getNode().text, getNode().values);
	}
	
	function zoomed() {
		g.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
	}
	
	function dragstarted(d) {
		if (!d.root) {
		  d3.event.sourceEvent.stopPropagation();
		  d3.select(this).classed("dragging", true);
		}
	}

	function dragged(d) {
		if (!d.root) {
			d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
			tick();
		}
	}

	function dragended(d) {
		if (!d.root) {
			d3.select(this).classed("dragging", false);
		}
	}
	
	
	function setXY() {
		for (var i = 0; i < node.length; i++) {
			if (node[i].x == undefined) {
				node[i].x = w / 2 - (node[i].values / 2);
				node[i].y = h / 2 - (node[i].values / 2);
			}
		}
	}
	
	function linkNode() { // 선에 대한 정보를 제공하는 함수.
		links = [];
		for (var i = 0; i < node.length; i++) {
			if (node[i].child != undefined) { // undefined가 child가 존재하지 않냐? 고 묻는다.자식이없다면 아무것도 하지말고
				for (var y = 0; y < node[i].child.length; y++) { // undefined가 아니면, data에 넣은 links를 기준으로 선을 긋는다.자식이 있으면 자식의 수만큼 선을 그려줘라 
					links.push({
						source: node[i], // 부모 node에 대한 정보
		                target: node[node[i].child[y]] // 자식 node에 대한 정보
					});// links라는 배열 안에 source와 target에 해당하는 넣는다
				}
			};
		};
	}
	
	function tick() {	// '생각'을 drag 할 때, 계속해서 새로운 위치 정보를 받아와서 갱신해주는 함수.
		rect
			  .attr("cx", function(d) { return d.x; })
	          .attr("cy", function(d) { return d.y; });

		text
				.attr("dx", function(d) { return (d.x - (d.values / 3)); })
				.attr("dy", function(d) { return (d.y - (d.values / 4)); });
		
        line 
            .attr('x1', function(d){ return d.source.x; }) 
            .attr('y1', function(d){ return d.source.y; })
            .attr('x2', function(d){ return d.target.x; })
            .attr('y2', function(d){ return d.target.y; });
	}
	
	function reDraw() {
		var ts = g.attr("transform");
		
		setXY();
		linkNode();
		
		svg.remove();
		
		svg = d3.select("#main").append("svg")
											.attr("width", w)
											.attr("height", h);
		
		addg = svg.selectAll("g")
											.data(node)
											.enter()
												.append("g")
												.attr("transform", ts);
		g = svg.selectAll("g");
		
		rect = g.append("circle")
											.attr("r", function(d) { return d.values; })
											.attr("id", function(d) { if(d.root) {return "circle-root";} })
											.attr("cx", function(d) { return d.x; })
	    									.attr("cy", function(d) { return d.y; });
		
		line = g.selectAll("line")
											.data(links)
											.enter()
											.append("line")
											.style("pointer-events", "none");
		
		force = d3.layout.force()
					    .nodes(node)
					    .links(links)
					    .size([w,h]);
		
		text = g.append("text")
				.attr("dx", function(d) { return (d.x - (d.values / 3)); })
				.attr("dy", function(d) { return (d.y - (d.values / 4)); })
				.text(function(d, i) { return d.text; });
		
		force.on("tick", tick);
		svg.call(zoom);
		rect.call(drag);
	    rect.on("click", clicked);
	    
	    styleData();
	    tick();

	}
	
	function addData(text) {
		if (!isSelected()) {
			return;
		}
		node.push({
			"text" : text, "parent" : node.indexOf(selectednode), "x" : (selectednode.x + 150), "y" : (selectednode.y + 150), "values" : 70
		});
		if (selectednode.child != undefined) {
			selectednode.child.push(node.length-1); 
		} else {
			selectednode.child = [node.length-1];
		}
		selectednode = undefined;
		selected = undefined;
		
		reDraw();
	}
	
	function editData(text, values) {
		if (!isSelected()) {
			return;
		}
		selectednode.text = text;
		selectednode.values = parseInt(values);
		selectednode = undefined;
		selected = undefined;
			
		reDraw();
	}
	
	function styleData() {
		svg.selectAll("circle:not(#circle-root)").style("fill", function(d) {
			if (d.fill == undefined) {
				d.fill = circlefill;
				return circlefill;
			} else {
				return d.fill
			}
		});

		svg.selectAll("circle#circle-root").style("fill", function(d) {
			if (d.fill == undefined) {
				d.fill = rootfill;
				return rootfill;
			} else {
				return d.fill;
			}
		})
		.style("stroke", function(d) {
			if (d.stroke == undefined) {
				d.stroke = rootstroke;
				return rootstroke;
			} else {
				return d.stroke;
			}
		})
		.style("stroke-width", function(d) {
			if (d.stroke_width == undefined) {
				d.stroke_width = rootstroke_width;
				return rootstroke_width;
			} else {
				return d.stroke_width;
			}
		})
		.style("stroke-dasharray", function(d) {
			if (d.stroke_dasharray == undefined) {
				d.stroke_dasharray = rootstroke_dasharray;
				return rootstroke_dasharray;
			} else {
				return d.stroke_dasharray;
			}
		});

		g.selectAll("line")
		.style("stroke", function(d) {
			if (d.stroke == undefined) {
				d.stroke = linestroke;
				return linestroke;
			} else {
				return d.stroke;
			}
		})
		.style("stroke-width", function(d) {
			if (d.stroke_width == undefined) {
				d.stroke_width = linestroke_width;
				return linestroke_width;
			} else {
				return d.stroke_width;
			}
		})
		.style("shape-rendering", function(d) {
			if (d.shape_rendering == undefined) {
				d.shape_rendering = lineshape_rendering; 
				return lineshape_rendering;
			} else {
				return d.shape;
			}
		})
		.style("opacity", function(d) {
			if (d.opacity == undefined) {
				d.opacity = lineopacity;
				return lineopacity;
			} else {
				return d.opacity;
			}
		});

		g.append("text")
		.style("fill", function(d) {
			if (d.fill == undefined) {
				d.fill = textfill;
				return textfill;
			} else {
				return d.fill;
			}
		})
		.style("font-size", function(d) {
			if (d.font_size == undefined) {
				d.font_size = textfont_size;
				return textfont_size;
			} else {
				return d.font_size;
			}
		})
		.style("font-family", function(d) {
			if (d.font_family == undefined) {
				d.font_family = textfont_family; 
				return textfont_family;
			} else {
				return d.font_family;
			}
		})
		.style("font", function(d) {
			if (d.font == undefined) {
				d.font = textfont;
				return textfont;
			} else {
				return d.font;
			}
		});
	}
	
	function getNode() {
		return selectednode;
	}
	
	function deleteData() {
		if (!isSelected()) {
			return;
		}
		if (selectednode.root) {
			return;
		}
		var index = node.indexOf(selectednode);
		var selected_child = undefined;
		if (node[index].child != undefined) {
			 selected_child = node[index].child; 
		} 
		
		// remove selectednode from parent
		node[selectednode.parent].child.splice(node[selectednode.parent].child.indexOf(index), 1);
		
		// function about delete childs
		var array_todelete = [];
		var delete_child = function(array_child) {
				for (var i in array_child) {
					if (node[array_child[i]].child != undefined) {
						delete_child(node[array_child[i]].child);
					}
					array_todelete.push(node.indexOf(node[array_child[i]]));
				}
		}
		if (selected_child != undefined) { delete_child(selected_child); }
		
		// delete list (sorted)
		array_todelete.push(index);
		array_todelete.sort(function(a,b) {
			return b - a;
		});
		
		
		// delete and reorder nodes
		for (var loop in array_todelete) {
			node.splice(array_todelete[loop], 1);
			
			for (var int = 0; int < node.length; int++) {
				if (node[int].parent > array_todelete[loop]) {
					node[int].parent = (node[int].parent - 1);
				}
				if (node[int].child != undefined) {
					for (var y = 0; y < node[int].child.length; y++) {
						if (node[int].child[y] > array_todelete[loop]) {
							node[int].child[y] = (node[int].child[y] - 1);
						}
					}
				}
			} 
		}
		
		selectednode = undefined;
		reDraw();
	}
	
	function isSelected() {
		var result = false;
		if (selectednode == undefined) {
			alert("먼저 생각을 선택해주세요.");
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	
	function isRoot() {
		return selectednode.root;
	}
	
	function setViewpoint(node) {
		var xy = [];
		var t = undefined;
		if (arguments.length != 0) {
			xy = [node.x, node.y];
		} else {
			xy.push(0);
			xy.push(0);
		}
		t = "translate(" + xy +")scale(" + 1 + ")";	
		
		var zoom = d3.behavior.zoom()
		.scale("1")
		.translate(xy)
	    .scaleExtent(zoomrate)
	    .on("zoom", zoomed);
		
		var translate = [0, 0];
		g.transition()
			.duration(700)
			.attr("transform", t);
		
		svg.call(zoom);
		tick();
	}
	
	tick();