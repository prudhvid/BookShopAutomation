// Minified using Javascript Aggregator - see /files/js/js_4b8babbd1d451e3d408a903375664f54.js for original source including licensing information.

Drupal.behaviors.odbcache=function(){var classes_to_hide=null;var classes_to_remove=null;var cookie=document.cookie;if(cookie&&cookie.match("odb=")){$(".guest").hide();$(".user").show();if(cookie.match("odb=editor")){$("#tab-select").show();$("#tab-select option").hide();$("#tab-select option.view").show();$("#tab-select option.edit").show();$("#tab-select option.revisions").show();$("#share").hide();}
if(document.cookie.match("odb=super")){$(".super").show();$(".nuper").hide();$("#tab-select").show();$(document).keydown(function(e){if(e.keyCode==113){$("#superfish").load("/odbmenu/admin_menu");initSuperFish();}});$("#share").hide();}
else{classes_to_hide=".super";classes_to_remove="SELECT .super";}}
else{classes_to_hide=".super, .user";classes_to_remove="SELECT .super, SELECT .user";}
if(classes_to_hide){$('#superfish .super').parent().hide();$(classes_to_hide).hide();}
if(classes_to_remove){$(classes_to_remove).remove();}
if(document.cookie&&document.cookie.match("odb=")){var userCookie=document.cookie.match("odb=user_\\d*");if(userCookie){var cls=".super.u"+userCookie.toString().substr(9);$(cls).show();}}};;Drupal.behaviors.odbutil_general=function(){var uri=location.toString();if(uri.match("edit")||uri.match("new")&&document.cookie.match("odb=super")){var headTitleLabel=$("#edit-field-head-title-0-value-wrapper label");var headTitleTextField=$("#edit-field-head-title-0-value");if(headTitleLabel.length>0&&headTitleTextField.length>0){var countChars=function(e){headTitleLabel.text("Head Title <"+
headTitleTextField.val().length+" characters>:");};countChars();$(headTitleTextField).keyup(countChars);}
var bodyLabel=$("#edit-body-wrapper label");var bodyTextArea=$("#edit-body");if(bodyLabel.length>0&&bodyTextArea.length>0){var countWords=function(e){var text=bodyTextArea.val();text=text.replace(/&(lt|gt);/g,function(strMatch,p1){return(p1=="lt")?"<":">";});text=text.replace(/<\/?[^>]+(>|$)/g,"");text=text.replace(/\s/g,' ');text=text.split(' ');var count=0;for(var i=0;i<text.length;i++){if(text[i].length>0)count++;}
bodyLabel.text("Body <"+count+" words>:");};countWords();$(bodyTextArea).keyup(countWords);}}};var content=$("#content");content.html(content.html().replace(/(\d{4})\-(\d{2})\-(\d{2}) (\d{2})\:(\d{2})/g,function getLocalDate(s,y,m,d,h,i){var time=Date.UTC(y,m-1,d,h,i,0);var date=new Date(time);return date.getFullYear()+"-"+
zeroPad(date.getMonth()+1,2)+"-"+
zeroPad(date.getDate(),2)+" "+
zeroPad(date.getHours(),2)+":"+
zeroPad(date.getMinutes(),2);}));function parseInt2(s,pos){if(s.charAt(pos)!='0'){return parseInt(s.substr(pos,2));}
return parseInt(s.substr(pos+1,1));}
function zeroPad(num,count){var numZeropad=num+'';while(numZeropad.length<count){numZeropad="0"+numZeropad;}
return numZeropad;}
Drupal.behaviors.extlink=function(){var host=window.location.host.replace(/^(([^\/]+?\.)*)([^\.]{4,})((\.[a-z]{1,4})*)$/,'$3$4');var subdomains="([^/]*\\.)?";var internal_link=new RegExp("^https?://"+subdomains+host,"i");var external_links=new Array();var mailto_links=new Array();$("a:not(.ext, .mailto)").each(function(el){try{var url=this.href.toLowerCase();if(url.indexOf('http')==0&&(!url.match(internal_link))){external_links.push(this);}
else if(url.indexOf('mailto:')==0){mailto_links.push(this);}}
catch(error){return false;}});$(external_links).not($(external_links).find('img').parents('a')).addClass("ext").each(function(){if($(this).css('display')=='inline'){$(this).after('<span class="ext"></span>');}});$(mailto_links).not($(mailto_links).find('img').parents('a')).addClass("mailto").each(function(){if($(this).css('display')=='inline'){$(this).after('<span class="mailto"></span>');}});};var RUZEE=window.RUZEE||{};RUZEE.ShadedBorder={create:function(opts){var isie=/msie/i.test(navigator.userAgent)&&!window.opera;var isie6=isie&&!window.XMLHttpRequest;function sty(el,h){for(k in h){if(/ie_/.test(k)){if(isie)el.style[k.substr(3)]=h[k];}else el.style[k]=h[k];}}
function crdiv(h){var el=document.createElement("div");el.className="sb-gen";sty(el,h);return el;}
function op(v){v=v<0?0:v;if(v>0.99999)return"";return isie?" filter:alpha(opacity="+(v*100)+");":" opacity:"+v+';';}
var sr=opts.shadow||0;var r=opts.corner||0;var bor=0;var bow=opts.border||0;var boo=opts.borderOpacity||1;var shadow=sr!=0;var lw=r>sr?r:sr;var rw=lw;var th=lw;var bh=lw;if(bow>0){bor=r;r=r-bow;}
var cx=r!=0&&shadow?Math.round(lw/3):0;var cy=cx;var cs=Math.round(cx/2);var iclass=r>0?"sb-inner":"sb-shadow";var sclass="sb-shadow";var bclass="sb-border";var edges=opts.edges||"trlb";if(!/t/i.test(edges))th=0;if(!/b/i.test(edges))bh=0;if(!/l/i.test(edges))lw=0;if(!/r/i.test(edges))rw=0;var p={position:"absolute",left:"0",top:"0",width:lw+"px",height:th+"px",ie_fontSize:"1px",overflow:"hidden",margin:"0",padding:"0"};var tl=crdiv(p);delete p.left;p.right="0";p.width=rw+"px";var tr=crdiv(p);delete p.top;p.bottom="0";p.height=bh+"px";var br=crdiv(p);delete p.right;p.left="0";p.width=lw+"px";var bl=crdiv(p);var tw=crdiv({position:"absolute",width:"100%",height:th+"px",ie_fontSize:"1px",top:"0",left:"0",overflow:"hidden",margin:"0",padding:"0"});var t=crdiv({position:"relative",height:th+"px",ie_fontSize:"1px",margin:"0 "+rw+"px 0 "+lw+"px",overflow:"hidden",padding:"0"});tw.appendChild(t);var bw=crdiv({position:"absolute",left:"0",bottom:"0",width:"100%",height:bh+"px",ie_fontSize:"1px",overflow:"hidden",margin:"0",padding:"0"});var b=crdiv({position:"relative",height:bh+"px",ie_fontSize:"1px",margin:"0 "+rw+"px 0 "+lw+"px",overflow:"hidden",padding:"0"});bw.appendChild(b);var mw=crdiv({position:"absolute",top:(-bh)+"px",left:"0",width:"100%",height:"100%",overflow:"hidden",ie_fontSize:"1px",padding:"0",margin:"0"});function corner(el,t,l){var w=l?lw:rw;var h=t?th:bh;var s=t?cs:-cs;var dsb=[];var dsi=[];var dss=[];var xp=0;var xd=1;if(l){xp=w-1;xd=-1;}
for(var x=0;x<w;++x){var yp=h-1;var yd=-1;if(t){yp=0;yd=1;}
var finished=false;for(var y=h-1;y>=0&&!finished;--y){var div='<div style="position:absolute; top:'+yp+'px; left:'+xp+'px; '+'width:1px; height:1px; overflow:hidden; margin:0; padding:0;';var xc=x-cx;var yc=y-cy-s;var d=Math.sqrt(xc*xc+yc*yc);var doShadow=false;if(r>0){if(xc<0&&yc<bor&&yc>=r||yc<0&&xc<bor&&xc>=r){dsb.push(div+op(boo)+'" class="'+bclass+'"></div>');}else
if(d<bor&&d>=r-1&&xc>=0&&yc>=0){var dd=div;if(d>=bor-1){dd+=op((bor-d)*boo);doShadow=true;}else dd+=op(boo);dsb.push(dd+'" class="'+bclass+'"></div>');}
var dd=div+' z-index:2;'+(t?'background-position:0 -'+(r-yc-1)+'px;':'background-image:none;');var finish=function(){if(!t)dd=dd.replace(/top\:\d+px/,"top:0px");dd=dd.replace(/height\:1px/,"height:"+(y+1)+"px");dsi.push(dd+'" class="'+iclass+'"></div>');finished=true;};if(xc<0&&yc<r||yc<0&&xc<r){finish();}else
if(d<r&&xc>=0&&yc>=0){if(d>=r-1){dd+=op(r-d);doShadow=true;dsi.push(dd+'" class="'+iclass+'"></div>');}else{finish();}}else doShadow=true;}else doShadow=true;if(sr>0&&doShadow){d=Math.sqrt(x*x+y*y);if(d<sr){dss.push(div+' z-index:0; '+op(1-(d/sr))+'" class="'+sclass+'"></div>');}}
yp+=yd;}
xp+=xd;}
el.innerHTML=dss.concat(dsb.concat(dsi)).join('');}
function mid(mw){var ds=[];ds.push('<div style="position:relative; top:'+(th+bh)+'px; height:2048px; '+' margin:0 '+(rw-r-cx)+'px 0 '+(lw-r-cx)+'px; '+' padding:0; overflow:hidden;'+' background-position:0 '+(th>0?-(r+cy+cs):'0')+'px;"'+' class="'+iclass+'"></div>');var dd='<div style="position:absolute; width:1px;'+' top:'+(th+bh)+'px; height:2048px; padding:0; margin:0;';if(sr>0){for(var x=0;x<lw-r-cx;++x){ds.push(dd+' left:'+x+'px;'+op((x+1.0)/lw)+'" class="'+sclass+'"></div>');}
for(var x=0;x<rw-r-cx;++x){ds.push(dd+' right:'+x+'px;'+op((x+1.0)/rw)+'" class="'+sclass+'"></div>');}}
if(bow>0){var su=' width:'+bow+'px;'+op(boo)+'" class="'+bclass+'"></div>';ds.push(dd+' left:'+(lw-bor-cx)+'px;'+su);ds.push(dd+' right:'+(rw-bor-cx)+'px;'+su);}
mw.innerHTML=ds.join('');}
function tb(el,t){var ds=[];var h=t?th:bh;var dd='<div style="height:1px; overflow:hidden; position:absolute; margin:0; padding:0;'+' width:100%; left:0px; ';var s=t?cs:-cs;for(var y=0;y<h-s-cy-r;++y){if(sr>0)ds.push(dd+(t?'top:':'bottom:')+y+'px;'+op((y+1)*1.0/h)+'" class="'+sclass+'"></div>');}
if(y>=bow){ds.push(dd+(t?'top:':'bottom:')+(y-bow)+'px;'+op(boo)+' height:'+bow+'px;" class="'+bclass+'"></div>');}
ds.push(dd+(t?'background-position-y:0; top:':'background-image:none; bottom:')+y+'px;'+' height:'+(r+cy+s)+'px;" class="'+iclass+'"></div>');el.innerHTML=ds.join('');}
corner(tl,true,true);corner(tr,true,false);corner(bl,false,true);corner(br,false,false);mid(mw);tb(t,true);tb(b,false);needsCloning=false;return{render:function(el){if(typeof el=='string')el=document.getElementById(el);if(el.length!=undefined){for(var i=0;i<el.length;++i)this.render(el[i]);return;}
el.className+=" sb";sty(el,{position:"relative",background:"transparent"});var node=el.firstChild;while(node){var nextNode=node.nextSibling;if(node.nodeType==1&&node.className=='sb-gen')
el.removeChild(node);node=nextNode;}
var iel=el.firstChild;var twc=needsCloning?tw.cloneNode(true):tw;var mwc=needsCloning?mw.cloneNode(true):mw;var bwc=needsCloning?bw.cloneNode(true):bw;var tlc=needsCloning?tl.cloneNode(true):tl;var trc=needsCloning?tr.cloneNode(true):tr;var blc=needsCloning?bl.cloneNode(true):bl;var brc=needsCloning?br.cloneNode(true):br;el.insertBefore(tlc,iel);el.insertBefore(trc,iel);el.insertBefore(blc,iel);el.insertBefore(brc,iel);el.insertBefore(twc,iel);el.insertBefore(mwc,iel);el.insertBefore(bwc,iel);if(isie6){el.onmouseover=function(){this.className+=" hover";}
el.onmouseout=function(){this.className=this.className.replace(/ hover/,"");}}
if(isie){function resize(){twc.style.width=bwc.style.width=mwc.style.width=el.offsetWidth+"px";if(isie6){mwc.firstChild.style.height=el.offsetHeight+"px";}else{for(var i=0;i<mwc.childNodes.length;++i){mwc.childNodes[i].style.height=(el.offsetHeight-bh-th)+"px";}}
trc.style.right=brc.style.right=null;trc.style.left=brc.style.left=(el.offsetWidth-rw)+"px";}
el.onresize=resize;resize();}
needsCloning=true;}};}}
document.write('\
  <style type="text/css">\
  .sb, .sbi, .sb *, .sbi * { position:relative; z-index:1; }\
  * html .sb, * html .sbi { height:1%; }\
  .sbi { display:inline-block; }\
  .sb-inner { background:#ddd; }\
  .sb-shadow { background:#000; }\
  .sb-border { background:#bbb; }\
  </style>\
');;Drupal.behaviors.odbuser=function(){if(location.toString().match("/user/login")){if(!$.browser.msie||$.browser.version!="6.0"){var shadedBorder=RUZEE.ShadedBorder.create({corner:8,border:2});shadedBorder.render("login-block");shadedBorder.render("password-block");shadedBorder.render("register-block");}}
$("#user-profile-form #edit-pass-pass1").val("");};;Drupal.behaviors.odbdoc=function(){if(location.toString().match("api/")){$("div.member").each(function(){var memberId="#"+this.id;$(memberId+" span.name a").click(function(e){if(e.ctrlKey||e.shiftKey){return true;}
else{var details=$(memberId+" div.details");details.toggle();$(memberId+" div.teaser").toggle();return false;}});});}};function addSelectedNode(id,node_title_and_id){for(var i=0;i<100;i++){var editId='#edit-field-related-nodes-'+i+'-nid-nid';if($(editId).val()==''){$(editId).val(node_title_and_id);$('#r_'+id).hide();break;}}};Drupal.behaviors.odbsearch=function(){$("#search-submit").val('').focus(function(){$("#search-submit").blur();});var edit=$("#search-text");var url=window.location.toString();if(url.search(/.*object\/database\//g)>=0){edit.val(url.replace(/.*object\/database\//g,'').replace(/\//g,' '));}
else{edit.val('search...');edit.one('focus',0,function(){$("#search-box :text").val('');});}
$("#auto-search").html("222");};;Hilite={elementid:'content',exact:true,max_nodes:1000,onload:true,style_name:'hilite',style_name_suffix:false,debug_referrer:''};Hilite.search_engines=[['google\\.','q'],['search\\.yahoo\\.','p'],['search\\.msn\\.','q'],['search\\.live\\.','query'],['search\\.aol\\.','userQuery'],['ask\\.com','q'],['altavista\\.','q'],['feedster\\.','q'],['search\\.lycos\\.','q'],['alltheweb\\.','q'],['technorati\\.com/search/([^\\?/]+)',1],['dogpile\\.com/info\\.dogpl/search/web/([^\\?/]+)',1,true]];Hilite.decodeReferrer=function(referrer){var query=null;var regex=new RegExp('');for(var i=0;i<Hilite.search_engines.length;i++){var se=Hilite.search_engines[i];regex.compile('^http://(www\\.)?'+se[0],'i');var match=referrer.match(regex);if(match){var result;if(isNaN(se[1])){result=Hilite.decodeReferrerQS(referrer,se[1]);}else{result=match[se[1]+1];}
if(result){result=decodeURIComponent(result);if(se.length>2&&se[2])
result=decodeURIComponent(result);result=result.replace(/\'|"/g,'');result=result.split(/[\s,\+\.]+/);return result;}
break;}}
return null;};Hilite.decodeReferrerQS=function(referrer,match){var idx=referrer.indexOf('?');var idx2;if(idx>=0){var qs=new String(referrer.substring(idx+1));idx=0;idx2=0;while((idx>=0)&&((idx2=qs.indexOf('=',idx))>=0)){var key,val;key=qs.substring(idx,idx2);idx=qs.indexOf('&',idx2)+1;if(key==match){if(idx<=0){return qs.substring(idx2+1);}else{return qs.substring(idx2+1,idx-1);}}}}
return null;};Hilite.hiliteElement=function(elm,query){if(!query||elm.childNodes.length==0)
return;var qre=new Array();for(var i=0;i<query.length;i++){query[i]=query[i].toLowerCase();if(Hilite.exact)
qre.push('\\b'+query[i]+'\\b');else
qre.push(query[i]);}
qre=new RegExp(qre.join("|"),"i");var stylemapper={};for(var i=0;i<query.length;i++){if(Hilite.style_name_suffix)
stylemapper[query[i]]=Hilite.style_name+(i+1);else
stylemapper[query[i]]=Hilite.style_name;}
var textproc=function(node){var match=qre.exec(node.data);if(match){var val=match[0];var k='';var node2=node.splitText(match.index);var node3=node2.splitText(val.length);var span=node.ownerDocument.createElement('SPAN');node.parentNode.replaceChild(span,node2);span.className=stylemapper[val.toLowerCase()];span.appendChild(node2);return span;}else{return node;}};Hilite.walkElements(elm.childNodes[0],1,textproc);};Hilite.hilite=function(){var q=Hilite.debug_referrer?Hilite.debug_referrer:document.referrer;var e=null;q=Hilite.decodeReferrer(q);if(q&&((Hilite.elementid&&(e=document.getElementById(Hilite.elementid)))||(e=document.body)))
{Hilite.hiliteElement(e,q);}};Hilite.walkElements=function(node,depth,textproc){var skipre=/^(script|style|textarea)/i;var count=0;while(node&&depth>0){count++;if(count>=Hilite.max_nodes){var handler=function(){Hilite.walkElements(node,depth,textproc);};setTimeout(handler,50);return;}
if(node.nodeType==1){if(!skipre.test(node.tagName)&&node.childNodes.length>0){node=node.childNodes[0];depth++;continue;}}else if(node.nodeType==3){node=textproc(node);}
if(node.nextSibling){node=node.nextSibling;}else{while(depth>0){node=node.parentNode;depth--;if(node.nextSibling){node=node.nextSibling;break;}}}}};if(Hilite.onload){if(window.attachEvent){window.attachEvent('onload',Hilite.hilite);}else if(window.addEventListener){window.addEventListener('load',Hilite.hilite,false);}else{var __onload=window.onload;window.onload=function(){Hilite.hilite();__onload();};}};Drupal.behaviors.odbpost=function(){if(location.toString().match("database/")){var s=$("#subscription-info");if(s.size()>0&&s.html().match(/subscription/)){subscription_load();}}};$(function(){if(location.toString().match("database/")){$("#post-reply .node-form").hide();$("#post-reply h2 a").click(function(e){$(".node-form").toggle();});}});function subscription_load(){var subscription=$("#subscription");if(subscription.is(':visible'))
{var url=$("#subscription-info").html();subscription.load(url);}}
function subscription_remove(nid,uid){var tr=$("tr:has(a.n"+nid+")");var tbody=tr.parent();$.get("/unsubscribe/"+nid+"/"+uid,function(){tr.hide();if(tbody.children("tr:visible").size()==0){location.reload(true);}});};